package net.yeoman.nmpcaport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="funds")
public class FundEntity implements Serializable {

    private static final long serialVersionUID = -8744722534402007944L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "required")
    private String fundId;

    @NotBlank(message = "required")
    private String name;

    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;


    @OneToMany(mappedBy = "fundEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SiteFundingDetailsEntity> siteFundingDetails;


    @PrePersist
    protected void onCreate(){

        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdated(){

        this.updatedAt = new Date();
    }

    public FundEntity() {
    }

    public FundEntity(Long id,
                      String fundId,
                      String name,
                      Date createdAt,
                      Date updatedAt,
                      List<SiteFundingDetailsEntity> siteFundingDetails) {
        this.id = id;
        this.fundId = fundId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.siteFundingDetails = siteFundingDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<SiteFundingDetailsEntity> getSiteFundingDetails() {
        return siteFundingDetails;
    }

    public void setSiteFundingDetails(List<SiteFundingDetailsEntity> siteFundingDetails) {
        this.siteFundingDetails = siteFundingDetails;
    }
}
