package edu.najah.cs.special_cook_pms.model;

public class Chef {

    private final String name;
    private String workload;
    private final String expertise;

    public Chef(String name, String workload, String expertise) {
        this.name = name;
        this.workload = workload;
        this.expertise = expertise;
    }

    public String getName() {
        return name;
    }

    public String getWorkload() {
        return workload;
    }

    public String getExpertise() {
        return expertise;
    }

    public void increaseWorkload() {
        switch (workload) {
            case "Low":
                workload = "Medium";
                break;
            case "Medium":
                workload = "High";
                break;
        }
    }

    public boolean isAvailable() {
        return !"High".equalsIgnoreCase(workload);
    }
}
