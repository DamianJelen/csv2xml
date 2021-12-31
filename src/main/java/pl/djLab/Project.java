package pl.djLab;

import pl.djLab.csv2xml.ObjEntities;
import pl.djLab.csv2xml.ObjProjects;
import pl.djLab.csv2xml.XmlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Project {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner write = new Scanner(System.in);
        String src = "", src2 = "";
        File file2 = null;

        System.out.println("Poniżej podaj ścieżkę do pliku wyeksporotwanego z raportu Arachne cz 1 Projects np. (\"C:\\arachne.csv\"),\n" +
                "jeżeli masz wyniki w raporcie Arachne cz 2 Entieties zapisz go w tym samym miejscu i pod taką samą nazwą co pierwsza analiza\n" +
                " z dopiskiem 2 np. (\"C:\\arachne2.csv\")");
        System.out.println("============================================================================================================================================");
//        C:\Users\damian_jelen\Documents\Zgłoszenia\ARACHNE\testArachne.csv
//        src = "C:\\Users\\damian_jelen\\Documents\\Zgłoszenia\\ARACHNE\\testArachne2.csv";
//        src = "C:\\Users\\damian_jelen\\Documents\\Zgłoszenia\\ARACHNE\\Arachne cz 1 Projects.csv";
        while(!new File(src).exists()) {
            System.out.print("Podaj scieżkę pliku csv z projektami  np. (\"C:\\projects.csv\"): ");
            src = write.nextLine();
            src2 = src.substring(0, src.lastIndexOf(".")) + 2 + src.substring(src.lastIndexOf("."));

            if(new File(src2).exists()) {
                file2 = new File(src2);
            } else {
                src2 = "";
            }
        }

        File file = new File(src);
        List<String> objProPartners = new ArrayList<>();
        Scanner readLineFile = new Scanner(file);
        XmlFile xmlF = new XmlFile();
        List<ObjProjects> listObjProjects = new ArrayList<>();
        List<ObjEntities> listObjEntieties = new ArrayList<>();
//        List<ObjEntities> listObjEntieties = src2.isEmpty() ? new ArrayList<>() : null;
        readLineFile.nextLine();
        String[] lineToArray = readLineFile.nextLine().split(",");

        xmlF.fileName = src.substring(src.lastIndexOf("\\") + 1, src.length() - 4) + ".xml";
        String metaDataXml = xmlF.metaDataXML(lineToArray[2],lineToArray[3],lineToArray[4].replace("&#44;",","),lineToArray[5],lineToArray[6]);
        ObjProjects objPro = new ObjProjects(lineToArray[7],lineToArray[8],lineToArray[9].replace("&#44;",","),lineToArray[10],lineToArray[11],lineToArray[13],lineToArray[14]);

        if (lineToArray.length == 16) {
            objPro.setRoadRailWasteType(lineToArray[15]);
        }

        if (!lineToArray[12].isEmpty()) {
            objProPartners.add(lineToArray[12]);
        }

        while (readLineFile.hasNextLine()) {
            String[] stringLine = readLineFile.nextLine().split(",");
            if (objPro.getProjectId().equals(stringLine[7])) {
                if(!stringLine[12].isEmpty()) {
                    objProPartners.add(stringLine[12]);
                }
            } else {
                objPro.setPartnerId(objProPartners);
                listObjProjects.add(objPro);

                objProPartners = new ArrayList<>();
                objPro = new ObjProjects(stringLine[7],stringLine[8],stringLine[9].replace("&#44;",","),stringLine[10],stringLine[11],stringLine[13],stringLine[14]);

                if (lineToArray.length == 16) {
                    objPro.setRoadRailWasteType(lineToArray[15]);
                }

                if (!stringLine[12].isEmpty()) {
                    objProPartners.add(stringLine[12]);
                }
            }
        }

        objPro.setPartnerId(objProPartners);
        listObjProjects.add(objPro);

        if(!src2.isEmpty()) {
            readLineFile = new Scanner(file2);
            readLineFile.nextLine();
            ObjEntities oneEntity = null;

            while(readLineFile.hasNextLine()) {
                String[] tmpStrArr = readLineFile.nextLine().split(",");
                System.out.println(Arrays.toString(tmpStrArr));
                oneEntity = new ObjEntities(tmpStrArr[0], tmpStrArr[1]);
                listObjEntieties.add(oneEntity);
            }

        }


        Path path = Paths.get("src/main/java/pl/djLab/xmlFiles/" + xmlF.fileName);
        try {
            Files.writeString(path, xmlF.FirstLineXML);
            Files.writeString(path, metaDataXml, StandardOpenOption.APPEND);

            Files.writeString(path, xmlF.ProjectsOpenLineXML, StandardOpenOption.APPEND);

            for (ObjProjects oneProject : listObjProjects) {
                Files.writeString(path, xmlF.projectsXML(oneProject), StandardOpenOption.APPEND);
            }

            Files.writeString(path, xmlF.ProjectsCloseLineXML, StandardOpenOption.APPEND);

            Files.writeString(path, xmlF.EntitiesOpenLineXML, StandardOpenOption.APPEND);

            Files.writeString(path, xmlF.entitiesXml(listObjEntieties), StandardOpenOption.APPEND);

            Files.writeString(path, xmlF.EntitiesCloseLineXML, StandardOpenOption.APPEND);

            Files.writeString(path, xmlF.LastLineXML, StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
