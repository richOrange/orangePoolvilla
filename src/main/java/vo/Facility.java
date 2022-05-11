package vo;

public class Facility {
   private int facilityNo;
   private String facilityName;
   private String updateDate;
   public Facility() {
      super();
      // TODO Auto-generated constructor stub
   }
   public Facility(int facilityNo, String facilityName, String updateDate) {
      super();
      this.facilityNo = facilityNo;
      this.facilityName = facilityName;
      this.updateDate = updateDate;
   }
   public int getFacilityNo() {
      return facilityNo;
   }
   public void setFacilityNo(int facilityNo) {
      this.facilityNo = facilityNo;
   }
   public String getFacilityName() {
      return facilityName;
   }
   public void setFacilityName(String facilityName) {
      this.facilityName = facilityName;
   }
   public String getUpdateDate() {
      return updateDate;
   }
   public void setUpdateDate(String updateDate) {
      this.updateDate = updateDate;
   }
   @Override
   public String toString() {
      return "Facility [facilityNo=" + facilityNo + ", facilityName=" + facilityName + ", updateDate=" + updateDate
            + "]";
   }

   
}
   
   
