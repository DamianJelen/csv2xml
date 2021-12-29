package pl.djLab.csv2xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class XmlFile {
    public String fileName;
    public final String FirstLineXML = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                                        "<ECDataExchangeXmlFormat xmlns=\"http://ecexchange.vadis.com\">\n";
    public final String ProjectsOpenLineXML = "\t<Projects>\n";
    public final String ProjectsCloseLineXML = "\t</Projects>\n";
    public final String EntitiesOpenLineXML = "\t<Entities>\n";
    public final String EntitiesCloseLineXML = "\t</Entities>\n";
    public final String LastLineXML = "</ECDataExchangeXmlFormat>";

    public String metaDataXML(String authors, String cciCode, String institution, String state, String currency) {
        Date getDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(getDate);

        return "\t<Source>" + fileName + "</Source>\n" +
                "\t<Date>" + date + "</Date>\n" +
                "\t<Author>" + authors + "</Author>\n" +
                "\t<OperationalProgramId>" + cciCode + "</OperationalProgramId>\n" +
                "\t<ManagingAuthorityId>" + institution + "</ManagingAuthorityId>\n" +
                "\t<MemberState>" + state + "</MemberState>\n" +
                "\t<Currency>" + currency + "</Currency>\n";
    }

    public String projectsXML(ObjProjects objProjects) {
        String block = "";

        if (objProjects.getProjectType().equals("1")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("2")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("3")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t\t<RoadType>" + objProjects.getRoadRailWasteType() + "</RoadType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("4")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t\t<RailType>" + objProjects.getRoadRailWasteType() + "</RailType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("5")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t\t<SolidWasteType>" + objProjects.getRoadRailWasteType() + "</SolidWasteType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("6")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("7")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t</Block>\n";
        } else if (objProjects.getProjectType().equals("9")) {
            block = "\t\t\t<Block>\n" +
                    "\t\t\t\t<Dg>" + objProjects.getDG() + "</Dg>\n" +
                    "\t\t\t\t<ProjectType>" + objProjects.getProjectType() + "</ProjectType>\n" +
                    "\t\t\t</Block>\n";
        }


        return "\t\t<Project>\n" +
                "\t\t\t<Id>" + objProjects.getProjectId() + "</Id>\n" +
                "\t\t\t<Status>" + objProjects.getProjectStatus() + "</Status>\n" +
                "\t\t\t<Name>" + objProjects.getProjectName() + "</Name>\n" +
                "\t\t\t<Beneficiary>\n" +
                "\t\t\t\t<EntityId>" + objProjects.getEntityId() + "</EntityId>\n" +
                "\t\t\t</Beneficiary>\n" +
                "\t\t\t<TotalCost>" + objProjects.getTotalCost() + "</TotalCost>\n" +
                projectPartners(objProjects.getPartnerId()) +
                block +
                "\t\t</Project>\n";
    }

    private String projectPartners(List<String> partners) {
        String resultString = "";

        if(partners.isEmpty()) {
            return "";
        }

        for (String partner : partners) {
            resultString += "\t\t\t\t<Partner>\n" +
                            "\t\t\t\t\t<Id>" + partner + "</Id>\n" +
                            "\t\t\t\t</Partner>\n";
        }


        return  "\t\t\t<Partners>\n" +
                resultString +
                "\t\t\t</Partners>\n";
    }

    public String entitiesXML(List<ObjEntities> entity) {
        String resultString = "";

        if(entity.isEmpty()) {
            return "";
        }

        for(ObjEntities entityObj : entity) {
            resultString += "\t\t<Entity>\n" +
                            "\t\t\t<Id>" + entityObj.getId() + "</Id>\n" +
                            "\t\t\t<Name>" + entityObj.getName() + "</Name>\n" +
                            "\t\t</Entity>\n";
        }

        return "\t<Entities>\n" +
                resultString +
                "\t</Entities>\n";
    }
}
