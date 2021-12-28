import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class JpgToPdf {
    public static void main(String arg[]) throws Exception {
        String currentPath = new java.io.File(".").getCanonicalPath();

        File root = new File(currentPath+"\\src\\main\\java");
        String outputFile = "Future Decleration.pdf";

        FilenameFilter jpgFilefilter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".jpg")) {
                return true;
            } else {
                return false;
            }
        };

        String[] imageFilesList = root.list(jpgFilefilter);
        assert imageFilesList != null;
        ArrayList<String> files = new ArrayList<>(Arrays.asList(imageFilesList));

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(root, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(root, f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsolute(PageSize.A4);
            document.add(image);
        }
        document.close();
    }
}
