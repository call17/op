package ru.sbertech.datahub.ui.model;

public class Project {
    private Long id;
    private Long idBlock;
    private String code;
    private String name;
    private String altName;
    private String validFrom;
    private String validTo;
    private int atPresent;
    private long idProgram;
    private String programCode;
    private String programName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public int getAtPresent() {
        return atPresent;
    }

    public void setAtPresent(int atPresent) {
        this.atPresent = atPresent;
    }

    public long getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(long idProgram) {
        this.idProgram = idProgram;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public Long getIdBlock() {
        return idBlock;
    }

    public void setIdBlock(Long idBlock) {
        this.idBlock = idBlock;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idBlock=" + idBlock +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", altName='" + altName + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                ", atPresent=" + atPresent +
                ", idProgram=" + idProgram +
                ", programCode='" + programCode + '\'' +
                ", programName='" + programName + '\'' +
                '}';
    }
}
