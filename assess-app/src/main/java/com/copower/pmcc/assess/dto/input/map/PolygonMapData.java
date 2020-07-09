package com.copower.pmcc.assess.dto.input.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        "path",
        "extData",
        "fillColor"
})
public class PolygonMapData implements Serializable {

    @JsonProperty("path")
    private List<List<Double>> path = new ArrayList<List<Double>>();
    @JsonProperty("extData")
    private ExtData extData;
    @JsonProperty("fillColor")
    private String fillColor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The path
     */
    @JsonProperty("path")
    public List<List<Double>> getPath() {
        return path;
    }

    /**
     *
     * @param path
     *     The path
     */
    @JsonProperty("path")
    public void setPath(List<List<Double>> path) {
        this.path = path;
    }

    /**
     *
     * @return
     *     The extData
     */
    @JsonProperty("extData")
    public ExtData getExtData() {
        return extData;
    }

    /**
     *
     * @param extData
     *     The extData
     */
    @JsonProperty("extData")
    public void setExtData(ExtData extData) {
        this.extData = extData;
    }

    /**
     *
     * @return
     *     The fillColor
     */
    @JsonProperty("fillColor")
    public String getFillColor() {
        return fillColor;
    }

    /**
     *
     * @param fillColor
     *     The fillColor
     */
    @JsonProperty("fillColor")
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
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
        return new HashCodeBuilder().append(path).append(extData).append(fillColor).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PolygonMapData) == false) {
            return false;
        }
        PolygonMapData rhs = ((PolygonMapData) other);
        return new EqualsBuilder().append(path, rhs.path).append(extData, rhs.extData).append(fillColor, rhs.fillColor).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}