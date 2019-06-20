
package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class AnalyzerLink {

    protected Integer dest;
    protected int distribution;
    protected double latency;
    protected int packetLoss;
    protected int power;
    protected int sf;
    protected Double snr;
    protected Integer source;

    /**
     * Gets the value of the dest property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDest() {
        return dest;
    }

    /**
     * Sets the value of the dest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDest(Integer value) {
        this.dest = value;
    }

    /**
     * Gets the value of the distribution property.
     * 
     */
    public int getDistribution() {
        return distribution;
    }

    /**
     * Sets the value of the distribution property.
     * 
     */
    public void setDistribution(int value) {
        this.distribution = value;
    }

    /**
     * Gets the value of the latency property.
     * 
     */
    public double getLatency() {
        return latency;
    }

    /**
     * Sets the value of the latency property.
     * 
     */
    public void setLatency(double value) {
        this.latency = value;
    }

    /**
     * Gets the value of the packetLoss property.
     * 
     */
    public int getPacketLoss() {
        return packetLoss;
    }

    /**
     * Sets the value of the packetLoss property.
     * 
     */
    public void setPacketLoss(int value) {
        this.packetLoss = value;
    }

    /**
     * Gets the value of the power property.
     * 
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the value of the power property.
     * 
     */
    public void setPower(int value) {
        this.power = value;
    }

    /**
     * Gets the value of the sf property.
     * 
     */
    public int getSF() {
        return sf;
    }

    /**
     * Sets the value of the sf property.
     * 
     */
    public void setSF(int value) {
        this.sf = value;
    }

    /**
     * Gets the value of the snr property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSNR() {
        return snr;
    }

    /**
     * Sets the value of the snr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSNR(Double value) {
        this.snr = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSource(Integer value) {
        this.source = value;
    }

}
