package com.copower.pmcc.assess.dto.input.map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "lat",
        "lng",
        "pid",
        "name"
})
public class Title implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("pid")
    private String pid;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The lat
     */
    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    @JsonProperty("lng")
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    @JsonProperty("lng")
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * @return The pid
     */
    @JsonProperty("pid")
    public String getPid() {
        return pid;
    }

    /**
     * @param pid The pid
     */
    @JsonProperty("pid")
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(lat).append(lng).append(pid).append(name).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Title) == false) {
            return false;
        }
        Title rhs = ((Title) other);
        return new EqualsBuilder().append(id, rhs.id).append(lat, rhs.lat).append(lng, rhs.lng).append(pid, rhs.pid).append(name, rhs.name).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}