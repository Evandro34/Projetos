package devandroid.evandro.procedimentosesus.api;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CriaPDF {

    private  Context context;

    private File pasta;
    private File arquivo;


    public CriaPDF(File pasta , Context context) {
        this.context = context;
        this.pasta = pasta;

    }

    public String salvarPNG(Bitmap bitmap,String nomeDoArquivo){

        arquivo = new File(pasta,nomeDoArquivo + ".png");

        try {
            arquivo.createNewFile();

            OutputStream streamDeSaida = new FileOutputStream(arquivo);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,streamDeSaida);
            streamDeSaida.close();


        } catch (IOException e) {

           return "" + e;
        }
        return "sucesso";
    }


}
