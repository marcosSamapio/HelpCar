package br.com.helpcar.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Called implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int calledId;
    private String brandVehicle;
    private String modelVehicle;
    private String calledDescription;
    private String photoOfVehicle;
    private Double latitude;
    private Double longitude;
    private int userId;

    @Ignore
    public Called(int calledId, String brandVehicle, String modelVehicle, String calledDescription, String photoOfVehicle, Double latitude, Double longitude, int userId) {
        this.calledId = calledId;
        this.brandVehicle = brandVehicle;
        this.modelVehicle = modelVehicle;
        this.calledDescription = calledDescription;
        this.photoOfVehicle = photoOfVehicle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
    }

    public Called() {

    }

    public int getCalledId() {
        return calledId;
    }

    public void setCalledId(int calledId) {
        this.calledId = calledId;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
