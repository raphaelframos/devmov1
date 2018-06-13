package com.raphaelframos.terceirao.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.raphaelframos.terceirao.R;
import com.raphaelframos.terceirao.banco_dados.BancoDeDados;
import com.raphaelframos.terceirao.model.Usuario;
import com.raphaelframos.terceirao.utils.GeralUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    private static final int RC_SIGN_IN = 123;
    private static final int CODIGO_IMAGEM = 423;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ImageView imageViewFoto;
    private FirebaseStorage storage;
    private Uri imagem;
    private Button buttonSalvar;
    private EditText editTextNome;
    private EditText editTextIdade;
    private Spinner spinnerEscola;
    private DatabaseReference myRef;
    private Usuario usuario;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        imageViewFoto = getView().findViewById(R.id.imageFoto);
        spinnerEscola = getView().findViewById(R.id.spinner_escola_usuario);
        buttonSalvar = getView().findViewById(R.id.button_salvar_perfil);
        editTextIdade = getView().findViewById(R.id.edit_text_idade);
        editTextNome = getView().findViewById(R.id.edit_text_nome_aluno);

        imageViewFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), CODIGO_IMAGEM);
            }
        });

        if(BancoDeDados.getInstance().temId(getActivity())){
            myRef = FirebaseDatabase.getInstance().getReference("usuarios").child(BancoDeDados.getInstance().getId(getActivity()));
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    usuario = dataSnapshot.getValue(Usuario.class);
                    try {
                        editTextIdade.setText(usuario.getIdade());
                        editTextNome.setText(usuario.getNome());
                        String escola = usuario.getEscola();
                        String[] escolas = getResources().getStringArray(R.array.escola);
                        int posicao = Arrays.asList(escolas).indexOf(escola);
                        spinnerEscola.setSelection(posicao);
                        Picasso.get().load(usuario.getFoto()).into(imageViewFoto);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            signIn();
        }

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(usuario == null){
                    usuario = new Usuario();
                }

                usuario.setEscola((String) spinnerEscola.getSelectedItem());
                usuario.setIdade(editTextIdade.getText().toString());
                usuario.setNome(editTextNome.getText().toString());
                if(imagem != null) {
                    usuario.setFoto(imagem.getPath());
                    mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                            StorageReference imagemRef = storage.getReference().child("images/"+BancoDeDados.getInstance().getId(getActivity()));
                            imagemRef.putFile(imagem).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    usuario.setFoto(taskSnapshot.getDownloadUrl().toString());
                                    salvaUsuario(usuario);
                                }
                            });
                        }
                    });
                }else{
                    salvaUsuario(usuario);
                }

                AlertDialog.Builder alerta = new AlertDialog.Builder(getActivity());
                alerta.setTitle("Usuário");
                alerta.setMessage("Usuário atualizado");
                alerta.setNeutralButton("Ok", null);
                alerta.show();



            }
        });

    }

    private void salvaUsuario(Usuario usuario) {
        myRef.setValue(usuario);
        BancoDeDados.getInstance().salvaNome(getActivity(), usuario.getNome());
        BancoDeDados.getInstance().salvaFoto(getActivity(), usuario.getFoto());
    }


    @Override
    public void onStart() {
        super.onStart();

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(account != null){
            salvaId(account);
            firebaseAuthWithGoogle(account);
        }

        // updateUI(account);
        // [END on_start_sign_in]
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try{
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (requestCode == CODIGO_IMAGEM) {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                {
                    try
                    {
                        imagem = data.getData();
                        Picasso.get().load(imagem).fit().centerCrop().into(imageViewFoto);

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            salvaId(account);
            myRef = FirebaseDatabase.getInstance().getReference("usuarios").child(BancoDeDados.getInstance().getId(getActivity()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void salvaId(GoogleSignInAccount account) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_config), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.id), account.getId());
        editor.apply();
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            //   updateUI(user);
                        } else {
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
