package com.example.hardyapplicationtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MembersResult {
    @SerializedName("memberData")
    @Expose
    List<Members> membersList;

    public MembersResult(List<Members> membersList) {
        this.membersList = membersList;
    }

    public List<Members> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Members> membersList) {
        this.membersList = membersList;
    }
}
