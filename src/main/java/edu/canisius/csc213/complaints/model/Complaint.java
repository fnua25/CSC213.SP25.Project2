package edu.canisius.csc213.complaints.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Arrays;

public class Complaint {

    @CsvBindByName(column = "Complaint ID")
    private long complaintId;

    @CsvBindByName(column = "Date received")
    private String dateReceivedStr; // Parsed later if needed

    @CsvBindByName(column = "Product")
    private String product;

    @CsvBindByName(column = "Sub-product")
    private String subProduct;

    @CsvBindByName(column = "Issue")
    private String issue;

    @CsvBindByName(column = "Sub-issue")
    private String subIssue;

    @CsvBindByName(column = "Consumer complaint narrative")
    private String narrative;

    @CsvBindByName(column = "Company public response")
    private String publicResponse;

    @CsvBindByName(column = "Company")
    private String company;

    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "ZIP code")
    private String zipCode;

    @CsvBindByName(column = "Tags")
    private String tags;

    @CsvBindByName(column = "Consumer consent provided?")
    private String consentProvided;

    @CsvBindByName(column = "Submitted via")
    private String submittedVia;

    @CsvBindByName(column = "Date sent to company")
    private String dateSentStr;

    @CsvBindByName(column = "Company response to consumer")
    private String companyResponse;

    @CsvBindByName(column = "Timely response?")
    private String timelyResponse;

    @CsvBindByName(column = "Consumer disputed?")
    private String disputed;

    // Embedding comes from the JSONL file
    private double[] embedding;

    // === Constructors ===

    public Complaint() {
        // Default constructor required for OpenCSV
    }

    public Complaint(long complaintId, String narrative, double[] embedding) {
        this.complaintId = complaintId;
        this.narrative = narrative;
        this.embedding = embedding;
    }

    // === Getters and Setters ===

    public long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(long complaintId) {
        this.complaintId = complaintId;
    }

    public String getDateReceivedStr() {
        return dateReceivedStr;
    }

    public void setDateReceivedStr(String dateReceivedStr) {
        this.dateReceivedStr = dateReceivedStr;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(String subProduct) {
        this.subProduct = subProduct;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getSubIssue() {
        return subIssue;
    }

    public void setSubIssue(String subIssue) {
        this.subIssue = subIssue;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getPublicResponse() {
        return publicResponse;
    }

    public void setPublicResponse(String publicResponse) {
        this.publicResponse = publicResponse;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getConsentProvided() {
        return consentProvided;
    }

    public void setConsentProvided(String consentProvided) {
        this.consentProvided = consentProvided;
    }

    public String getSubmittedVia() {
        return submittedVia;
    }

    public void setSubmittedVia(String submittedVia) {
        this.submittedVia = submittedVia;
    }

    public String getDateSentStr() {
        return dateSentStr;
    }

    public void setDateSentStr(String dateSentStr) {
        this.dateSentStr = dateSentStr;
    }

    public String getCompanyResponse() {
        return companyResponse;
    }

    public void setCompanyResponse(String companyResponse) {
        this.companyResponse = companyResponse;
    }

    public String getTimelyResponse() {
        return timelyResponse;
    }

    public void setTimelyResponse(String timelyResponse) {
        this.timelyResponse = timelyResponse;
    }

    public String getDisputed() {
        return disputed;
    }

    public void setDisputed(String disputed) {
        this.disputed = disputed;
    }

    public double[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(double[] embedding) {
        this.embedding = embedding;
    }

    // === toString() for debugging ===
    @Override
    public String toString() {
        return "Complaint{" +
                "ID=" + complaintId +
                ", product='" + product + '\'' +
                ", narrative='"
                + (narrative != null ? narrative.substring(0, Math.min(40, narrative.length())) + "..." : "null") + '\''
                +
                ", embedding preview="
                + (embedding != null ? Arrays.toString(Arrays.copyOf(embedding, Math.min(5, embedding.length))) + "..."
                        : "null")
                +
                '}';
    }

}
