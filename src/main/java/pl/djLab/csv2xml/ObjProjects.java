package pl.djLab.csv2xml;

import java.util.List;

public class ObjProjects {
    private String ProjectId,ProjectStatus,ProjectName,EntityId,TotalCost,DG,ProjectType,RoadRailWasteType;
    private List<String> PartnerId;

    public ObjProjects(String projectId, String projectStatus, String projectName, String entityId, String totalCost, String DG, String projectType) {
        ProjectId = projectId;
        ProjectStatus = projectStatus;
        ProjectName = projectName;
        EntityId = entityId;
        TotalCost = totalCost;
        this.DG = DG;
        ProjectType = projectType;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getProjectStatus() {
        return ProjectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        ProjectStatus = projectStatus;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getEntityId() {
        return EntityId;
    }

    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(String totalCost) {
        TotalCost = totalCost;
    }

    public String getDG() {
        return DG;
    }

    public void setDG(String DG) {
        this.DG = DG;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        ProjectType = projectType;
    }

    public String getRoadRailWasteType() {
        return RoadRailWasteType;
    }

    public void setRoadRailWasteType(String roadRailWasteType) {
        RoadRailWasteType = roadRailWasteType;
    }

    public List<String> getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(List<String> partnerId) {
        PartnerId = partnerId;
    }
}
