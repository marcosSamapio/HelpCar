package br.com.helpcar.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Called {
    @PrimaryKey(autoGenerate = true)
    private int calledId = 1;
    private String kindOfVehicle;
    private String brandVehicle;
    private String modelVehicle;
    private String calledDescription;
    private String photoOfVehicle;

    @Ignore
    public Called(int calledId, String kindOfVehicle, String brandVehicle, String modelVehicle, String calledDescription, String photoOfVehicle) {
        this.calledId = calledId;
        this.kindOfVehicle = kindOfVehicle;
        this.brandVehicle = brandVehicle;
        this.modelVehicle = modelVehicle;
        this.calledDescription = calledDescription;
        this.photoOfVehicle = photoOfVehicle;
    }

    public Called() {

    }

    public int getCalledId() {
        return calledId;
    }

    public void setCalledId(int calledId) {
        this.calledId = calledId;
    }

    public String getKindOfVehicle() {
        return kindOfVehicle;
    }

    public void setKindOfVehicle(String kindOfVehicle) {
        this.kindOfVehicle = kindOfVehicle;
    }

    public String getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(String brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

    public void setModelVehicle(String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    public String getCalledDescription() {
        return calledDescription;
    }

    public void setCalledDescription(String calledDescription) {
        this.calledDescription = calledDescription;
    }

    public String getPhotoOfVehicle() {
        return photoOfVehicle;
    }

    public void setPhotoOfVehicle(String photoOfVehicle) {
        this.photoOfVehicle = photoOfVehicle;
    }

    @Override
    public String toString() {
        return "Called{" +
                "calledId=" + calledId +
                ", brandVehicle='" + brandVehicle + '\'' +
                ", modelVehicle='" + modelVehicle + '\'' +
                ", problemDescription='" + calledDescription + '\'' +
                ", photoOfVehicle='" + photoOfVehicle + '\'' +
                '}';
    }
}
