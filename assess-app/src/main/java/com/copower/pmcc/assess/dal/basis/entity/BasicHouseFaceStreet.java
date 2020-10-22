package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BasicHouseFaceStreet implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 
     */
    private Integer houseId;

    /**
     * 街道名称
     */
    private String streetName;

    /**
     * 街道级别
     */
    private Integer streetLevel;

    /**
     * 距离
     */
    private String distance;

    /**
     * 交通流量
     */
    private Integer trafficFlow;

    /**
     * 人流量
     */
    private Integer visitorsFlowrate;

    /**
     * 方位
     */
    private Integer position;

    /**
     * 
     */
    private Boolean bisDelete;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * tb_basic_house_face_street
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return house_id 
     */
    public Integer getHouseId() {
        return houseId;
    }

    /**
     * 
     * @param houseId 
     */
    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    /**
     * 街道名称
     * @return street_name 街道名称
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * 街道名称
     * @param streetName 街道名称
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    /**
     * 街道级别
     * @return street_level 街道级别
     */
    public Integer getStreetLevel() {
        return streetLevel;
    }

    /**
     * 街道级别
     * @param streetLevel 街道级别
     */
    public void setStreetLevel(Integer streetLevel) {
        this.streetLevel = streetLevel;
    }

    /**
     * 距离
     * @return distance 距离
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 距离
     * @param distance 距离
     */
    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    /**
     * 交通流量
     * @return traffic_flow 交通流量
     */
    public Integer getTrafficFlow() {
        return trafficFlow;
    }

    /**
     * 交通流量
     * @param trafficFlow 交通流量
     */
    public void setTrafficFlow(Integer trafficFlow) {
        this.trafficFlow = trafficFlow;
    }

    /**
     * 人流量
     * @return visitors_flowrate 人流量
     */
    public Integer getVisitorsFlowrate() {
        return visitorsFlowrate;
    }

    /**
     * 人流量
     * @param visitorsFlowrate 人流量
     */
    public void setVisitorsFlowrate(Integer visitorsFlowrate) {
        this.visitorsFlowrate = visitorsFlowrate;
    }

    /**
     * 方位
     * @return position 方位
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 方位
     * @param position 方位
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 
     * @return bis_delete 
     */
    public Boolean getBisDelete() {
        return bisDelete;
    }

    /**
     * 
     * @param bisDelete 
     */
    public void setBisDelete(Boolean bisDelete) {
        this.bisDelete = bisDelete;
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public static BasicHouseFaceStreet.Builder builder() {
        return new BasicHouseFaceStreet.Builder();
    }

    /**
     * tb_basic_house_face_street
     */
    public static class Builder {
        /**
         * tb_basic_house_face_street
         */
        private BasicHouseFaceStreet obj;

        public Builder() {
            this.obj = new BasicHouseFaceStreet();
        }

        /**
         * id
         * @param id id
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 
         * @param houseId 
         */
        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        /**
         * 街道名称
         * @param streetName 街道名称
         */
        public Builder streetName(String streetName) {
            obj.setStreetName(streetName);
            return this;
        }

        /**
         * 街道级别
         * @param streetLevel 街道级别
         */
        public Builder streetLevel(Integer streetLevel) {
            obj.setStreetLevel(streetLevel);
            return this;
        }

        /**
         * 距离
         * @param distance 距离
         */
        public Builder distance(String distance) {
            obj.setDistance(distance);
            return this;
        }

        /**
         * 交通流量
         * @param trafficFlow 交通流量
         */
        public Builder trafficFlow(Integer trafficFlow) {
            obj.setTrafficFlow(trafficFlow);
            return this;
        }

        /**
         * 人流量
         * @param visitorsFlowrate 人流量
         */
        public Builder visitorsFlowrate(Integer visitorsFlowrate) {
            obj.setVisitorsFlowrate(visitorsFlowrate);
            return this;
        }

        /**
         * 方位
         * @param position 方位
         */
        public Builder position(Integer position) {
            obj.setPosition(position);
            return this;
        }

        /**
         * 
         * @param bisDelete 
         */
        public Builder bisDelete(Boolean bisDelete) {
            obj.setBisDelete(bisDelete);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        public BasicHouseFaceStreet build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        houseId("house_id", "houseId", "INTEGER", false),
        streetName("street_name", "streetName", "VARCHAR", false),
        streetLevel("street_level", "streetLevel", "INTEGER", false),
        distance("distance", "distance", "VARCHAR", false),
        trafficFlow("traffic_flow", "trafficFlow", "INTEGER", false),
        visitorsFlowrate("visitors_flowrate", "visitorsFlowrate", "INTEGER", false),
        position("position", "position", "INTEGER", false),
        bisDelete("bis_delete", "bisDelete", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_basic_house_face_street
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_basic_house_face_street
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_basic_house_face_street
         */
        private final String column;

        /**
         * tb_basic_house_face_street
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_basic_house_face_street
         */
        private final String javaProperty;

        /**
         * tb_basic_house_face_street
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}