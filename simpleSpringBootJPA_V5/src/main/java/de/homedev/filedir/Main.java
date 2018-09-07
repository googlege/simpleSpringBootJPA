package de.homedev.filedir;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            URL url = Main.class.getResource("/");
            System.err.println("url.toString():" + url.toString());
            System.err.println("url.getFile():" + url.getFile());
            System.err.println("url.getPath():" + url.getPath());

            final File runDir = new File(System.getProperty("user.dir"));
            final File absoluteBaseDirFile = new File(runDir, "filedir");
            System.err.println(absoluteBaseDirFile.toURI().toString());

            Path p = Paths.get(System.getProperty("user.dir"), "filedir");
            System.err.println(p.toUri().toString());

            //            Path p = Paths.get("H:\\vermop\\photon\\webservice\\build\\resources\\main\\filedata",
            //                "4/01A9668995499CDF534CEA082FEBCF7210B778DEFDA09FCE10301405C1F1C13B.png");
            //            File f = p.toFile();
            //            Path p= 
            //            System.err.println("f:" + f.getAbsolutePath());
            //            System.err.println("p:" + p.toUri().toString());

            Path pp = Paths.get("/files/4/resized", "165x165", "01A9668995499CDF534CEA082FEBCF7210B778DEFDA09FCE10301405C1F1C13B.png");
            System.err.println(pp.toString());
            System.err.println(pp.toUri().toURL());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
