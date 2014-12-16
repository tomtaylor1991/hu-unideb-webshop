package hu.unideb.webshop.fileupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.primefaces.model.UploadedFile;

public class CsvConverter {

    /**
     * Bekonvertalja egy feltoltott csv fajlt String tombok listajaba.
     *
     * @param uploadedFile
     * @param token
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> processCSV(UploadedFile uploadedFile,
            String token) throws IOException {
        try {
            ArrayList<String[]> ret = new ArrayList<String[]>();
            String str;
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    uploadedFile.getInputstream()));
            if (uploadedFile != null) {
                while ((str = reader.readLine()) != null) {
                    if (str.trim().length() > 0) {
                        ret.add(str.split(token));
                    }
                }
                return ret;
            }
        } finally {
            try {
                uploadedFile.getInputstream().close();
            } catch (Throwable ignore) {
            }
        }
        return null;
    }

    public static void testCSVPrint(ArrayList<String[]> data) {
        for (int i = 0; i < data.size(); i++) {
            //System.out.println(Arrays.toString(data.get(i)));
        }
    }
}
