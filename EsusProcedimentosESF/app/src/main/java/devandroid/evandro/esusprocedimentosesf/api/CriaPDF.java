package devandroid.evandro.esusprocedimentosesf.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CriaPDF {

    private Context context;

    private File pasta;
    private File arquivo;


    public CriaPDF(File pasta, Context context) {
        this.context = context;
        this.pasta = pasta;

        if (!pasta.exists()) {
            pasta.mkdirs();
        }

    }

    public String salvarPNG(Bitmap bitmap, String nomeDoArquivo) {

        arquivo = new File(pasta, nomeDoArquivo + ".png");

        try {
            arquivo.createNewFile();

            OutputStream streamDeSaida = new FileOutputStream(arquivo);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, streamDeSaida);
            streamDeSaida.close();


        } catch (IOException e) {

            return "" + e;
        }
        return "sucesso";
    }

    public String salvarPdf(Bitmap bitmap, String nomeDoArquivo) {


        arquivo = new File(pasta, nomeDoArquivo + ".pdf");

        PdfDocument arquivoPdf = new PdfDocument();
        PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page pagina = arquivoPdf.startPage(info);

        Canvas canvas = pagina.getCanvas();

        canvas.drawBitmap(bitmap, null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), null);

        arquivoPdf.finishPage(pagina);

        try {
            arquivo.createNewFile();
            OutputStream streamDeSaida = new FileOutputStream(arquivo);
            arquivoPdf.writeTo(streamDeSaida);
            streamDeSaida.close();
            arquivoPdf.close();


        } catch (IOException e) {

            return "" + e;
        }


        return "sucesso";

    }

}
