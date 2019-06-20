
package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class AnalyzerMote {

    protected double battery;
    protected int dataProbability;
    protected List<AnalyzerLink> links;
    protected int load;
    protected Integer moteid;
    protected int parents;

    /**
     * Gets the value of the battery property.
     * 
     */
    public double getBattery() {
        return battery;
    }

    /**
     * Sets the value of the battery property.
     * 
     */
    public void setBattery(double value) {
        this.battery = value;
    }

    /**
     * Gets the value of the dataProbability property.
     * 
     */
    public int getDataProbability() {
        return dataProbability;
    }

    /**
     * Sets the value of the dataProbability property.
     * 
     */
    public void setDataProbability(int value) {
        this.dataProbability = value;
    }

    /**
     * Gets the value of the links property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the links property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Link }
     * 
     * 
     */
    public List<AnalyzerLink> getLinks() {
        if (links == null) {
            links = new ArrayList<AnalyzerLink>();
        }
        return this.links;
    }

    /**
     * Gets the value of the load property.
     * 
     */
    public int getLoad() {
        return load;
    }

    /**
     * Sets the value of the load property.
     * 
     */
    public void setLoad(int value) {
        this.load = value;
    }

    /**
     * Gets the value of the moteid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMoteid() {
        return moteid;
    }

    /**
     * Sets the value of the moteid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMoteid(Integer value) {
        this.moteid = value;
    }

    /**
     * Gets the value of the parents property.
     * 
     */
    public int getParents() {
        return parents;
    }

    /**
     * Sets the value of the parents property.
     * 
     */
    public void setParents(int value) {
        this.parents = value;
    }

}
