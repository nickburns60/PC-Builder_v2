package org.example.models;

public class UserPcBuild {
    private int pcId;
    private int processorId;
    private int graphicsCardId;
    private int motherboardId;
    private int ramId;
    private int psuId;
    private int storageDriveId;
    private int caseId;
    private int fanId;
    private int cpuCoolerId;

    public UserPcBuild() {
    }

    public UserPcBuild(int pcId, int processorId, int graphicsCardId, int motherboardId, int ramId,
                       int psuId, int storageDriveId, int caseId, int fanId, int cpuCoolerId) {
        this.pcId = pcId;
        this.processorId = processorId;
        this.graphicsCardId = graphicsCardId;
        this.motherboardId = motherboardId;
        this.ramId = ramId;
        this.psuId = psuId;
        this.storageDriveId = storageDriveId;
        this.caseId = caseId;
        this.fanId = fanId;
        this.cpuCoolerId = cpuCoolerId;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getGraphicsCardId() {
        return graphicsCardId;
    }

    public void setGraphicsCardId(int graphicsCardId) {
        this.graphicsCardId = graphicsCardId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getPsuId() {
        return psuId;
    }

    public void setPsuId(int psuId) {
        this.psuId = psuId;
    }

    public int getStorageDriveId() {
        return storageDriveId;
    }

    public void setStorageDriveId(int storageDriveId) {
        this.storageDriveId = storageDriveId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getFanId() {
        return fanId;
    }

    public void setFanId(int fanId) {
        this.fanId = fanId;
    }

    public int getCpuCoolerId() {
        return cpuCoolerId;
    }

    public void setCpuCoolerId(int cpuCoolerId) {
        this.cpuCoolerId = cpuCoolerId;
    }
}
