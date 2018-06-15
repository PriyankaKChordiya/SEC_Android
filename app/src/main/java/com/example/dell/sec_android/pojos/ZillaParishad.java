package com.example.dell.sec_android.pojos;


public class ZillaParishad {
    private String name_of_electoral_div;

    private String division;

    private String electoral_div_id;

    private String sr_no;

    private String election_program_name;

    private String download;

    private String local_body;

    private String district;

    private String full_name;

    private String registeration_no;

    private String electoral_division_num;

    public ZillaParishad(String name_of_electoral_div, String electoral_div_id, String sr_no, String download, String full_name, String registeration_no) {
        this.name_of_electoral_div = name_of_electoral_div;
        this.electoral_div_id = electoral_div_id;
        this.sr_no = sr_no;
        this.download = download;
        this.full_name = full_name;
        this.registeration_no = registeration_no;
    }

    public String getName_of_electoral_div() {
        return name_of_electoral_div;
    }

    public void setName_of_electoral_div(String name_of_electoral_div) {
        this.name_of_electoral_div = name_of_electoral_div;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getElectoral_div_id() {
        return electoral_div_id;
    }

    public void setElectoral_div_id(String electoral_div_id) {
        this.electoral_div_id = electoral_div_id;
    }

    public String getSr_no() {
        return sr_no;
    }

    public void setSr_no(String sr_no) {
        this.sr_no = sr_no;
    }

    public String getElection_program_name() {
        return election_program_name;
    }

    public void setElection_program_name(String election_program_name) {
        this.election_program_name = election_program_name;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getLocal_body() {
        return local_body;
    }

    public void setLocal_body(String local_body) {
        this.local_body = local_body;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRegisteration_no() {
        return registeration_no;
    }

    public void setRegisteration_no(String registeration_no) {
        this.registeration_no = registeration_no;
    }

    public String getElectoral_division_num() {
        return electoral_division_num;
    }

    public void setElectoral_division_num(String electoral_division_num) {
        this.electoral_division_num = electoral_division_num;
    }

    @Override
    public String toString() {
        return local_body ;
    }
}




