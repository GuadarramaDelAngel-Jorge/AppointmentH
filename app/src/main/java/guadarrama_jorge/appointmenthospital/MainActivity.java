package guadarrama_jorge.appointmenthospital;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    TextView textTargetUri;
    ImageView targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.imag_paciente);
        textTargetUri = (TextView) findViewById(R.id.targeturi);
        targetImage = (ImageView) findViewById(R.id.Photo);
        String[] arraySpinner = new String[]{
                "Dr. Antonio", "Dra. Alejandra", "Dra. Berenice", "Dr. Daniel", "Dr. Ernesto"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void Toast(View v) {
        EditText Nombre = (EditText)findViewById(R.id.Nombre_Paciente);
        EditText Edad = (EditText)findViewById(R.id.Edad_Paciente);
        EditText Fecha = (EditText)findViewById(R.id.FechaCita_Paciente);
        EditText Hora = (EditText)findViewById(R.id.HoraCita_Paciente);
        EditText Telefono = (EditText)findViewById(R.id.Telefono_Paciente);
        EditText Correo = (EditText)findViewById(R.id.Correo_Paciente);
        EditText Direccion = (EditText)findViewById(R.id.Direccion_Paciente);
        EditText Padecimiento = (EditText)findViewById(R.id.Padecimiento_Paciente);
        Spinner Sp = (Spinner)findViewById(R.id.spinner);
        Toast.makeText(MainActivity.this, "Guardado el paciente "+Nombre.getText()+" de "+Edad.getText()+" años de edad, "+" para el dia "+
                        Fecha.getText()+" a las "+Hora.getText()+" Datos de Contacto: Celular:"+Telefono.getText()+" Correo:"+Correo.getText()+
                        " Dirección: "+Direccion.getText()+" Con el Padecimiento: "+Padecimiento.getText()+" Será atendido por el Doctor: "+Sp.getSelectedItem().toString(),
                Toast.LENGTH_LONG).show();

    }
}
