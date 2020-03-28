package upload;

public class ProjectExplain {
	  private String projectname;// 工程名称  
	    private String projectaddr;// 工程地点  
	    private Double projectamount;// 项目总金额  
	    private String otherinfo;  
	  
	    private String compilationbasis;// 编制依据  
	    private String construnit;// 建设单位  
	    private String costcheckunit;// 编审单位  
	    private String blueprint;// 图纸  
	  
	    private String designdate;// 设计日期  
	  
	    private String designation;// 图纸名称  
	  
	    private String pricingstandard;// 计价规范  
	  
	    private String valuationbasis;// 计价依据  
	  
	    private String pricesdate;// 材料价格取定时间  
	  
	    private String projectphase;// 项目阶段  
	  
	    private String costtype;// 造价类别  
	  
	    private String preparationtime;// 编制时间  
	  
	    private String flieno;// 文号  
	    private String valuationtype;// 计价方式  
	    private String designunit;// 设计单位  
	  
	    private String categoryName;// 项目大类型  
	    private String subcategoryName;// 项目类型  
	    private String projectphaseName;// 项目阶段  
	  
	    // 2015-7-14新增  
	    private String proUse;// 项目用途  
	    private String storeyHeight;// 层高(m)  
	    private Double storeyNum;// 层数  
	    private Double eavesHeight;// 檐高(m)  
	    private String startWorkDate;// 开工日期  
	    private String endWorkDate;// 竣工日期  
	    private String structureType;// 结构类型  
	    private String seismicIntensity;// 杭震烈度  
	    private String decorateStandard;// 装修标准  
	    private Double constructionArea;// 建筑面积  
	    private Double undergroundArea;// 地下建筑面积  
	    private Double overgroundArea;// 地上建筑面积  
	    private String areaUnit;//建筑面积单位  
	    private String overgroundStoreyNum;//地上层数  
	    private String undergroundStoreyNum;//地下层数  
	  
	      
	  
	    public String getUndergroundStoreyNum() {  
	        return undergroundStoreyNum;  
	    }  
	  
	    public void setUndergroundStoreyNum(String undergroundStoreyNum) {  
	        this.undergroundStoreyNum = undergroundStoreyNum;  
	    }  
	  
	    public String getOvergroundStoreyNum() {  
	        return overgroundStoreyNum;  
	    }  
	  
	    public void setOvergroundStoreyNum(String overgroundStoreyNum) {  
	        this.overgroundStoreyNum = overgroundStoreyNum;  
	    }  
	  
	    public String getProjectname() {  
	        return projectname;  
	    }  
	  
	    public void setProjectname(String projectname) {  
	        this.projectname = projectname;  
	    }  
	  
	    public String getProjectaddr() {  
	        return projectaddr;  
	    }  
	  
	    public void setProjectaddr(String projectaddr) {  
	        this.projectaddr = projectaddr;  
	    }  
	  
	    public Double getProjectamount() {  
	        return projectamount;  
	    }  
	  
	    public void setProjectamount(Double projectamount) {  
	        this.projectamount = projectamount;  
	    }  
	  
	    public String getOtherinfo() {  
	        return otherinfo;  
	    }  
	  
	    public void setOtherinfo(String otherinfo) {  
	        this.otherinfo = otherinfo;  
	    }  
	  
	    public String getCompilationbasis() {  
	        return compilationbasis;  
	    }  
	  
	    public void setCompilationbasis(String compilationbasis) {  
	        this.compilationbasis = compilationbasis;  
	    }  
	  
	    public String getConstrunit() {  
	        return construnit;  
	    }  
	  
	    public void setConstrunit(String construnit) {  
	        this.construnit = construnit;  
	    }  
	  
	    public String getCostcheckunit() {  
	        return costcheckunit;  
	    }  
	  
	    public void setCostcheckunit(String costcheckunit) {  
	        this.costcheckunit = costcheckunit;  
	    }  
	  
	    public String getBlueprint() {  
	        return blueprint;  
	    }  
	  
	    public void setBlueprint(String blueprint) {  
	        this.blueprint = blueprint;  
	    }  
	  
	    public String getDesigndate() {  
	        return designdate;  
	    }  
	  
	    public void setDesigndate(String designdate) {  
	        this.designdate = designdate;  
	    }  
	  
	    public String getDesignation() {  
	        return designation;  
	    }  
	  
	    public void setDesignation(String designation) {  
	        this.designation = designation;  
	    }  
	  
	    public String getPricingstandard() {  
	        return pricingstandard;  
	    }  
	  
	    public void setPricingstandard(String pricingstandard) {  
	        this.pricingstandard = pricingstandard;  
	    }  
	  
	    public String getValuationbasis() {  
	        return valuationbasis;  
	    }  
	  
	    public void setValuationbasis(String valuationbasis) {  
	        this.valuationbasis = valuationbasis;  
	    }  
	  
	    public String getPricesdate() {  
	        return pricesdate;  
	    }  
	  
	    public void setPricesdate(String pricesdate) {  
	        this.pricesdate = pricesdate;  
	    }  
	  
	    public String getProjectphase() {  
	        return projectphase;  
	    }  
	  
	    public void setProjectphase(String projectphase) {  
	        this.projectphase = projectphase;  
	    }  
	  
	    public String getCosttype() {  
	        return costtype;  
	    }  
	  
	    public void setCosttype(String costtype) {  
	        this.costtype = costtype;  
	    }  
	  
	    public String getPreparationtime() {  
	        return preparationtime;  
	    }  
	  
	    public void setPreparationtime(String preparationtime) {  
	        this.preparationtime = preparationtime;  
	    }  
	  
	    public String getFlieno() {  
	        return flieno;  
	    }  
	  
	    public void setFlieno(String flieno) {  
	        this.flieno = flieno;  
	    }  
	  
	    public String getValuationtype() {  
	        return valuationtype;  
	    }  
	  
	    public void setValuationtype(String valuationtype) {  
	        this.valuationtype = valuationtype;  
	    }  
	  
	    public String getDesignunit() {  
	        return designunit;  
	    }  
	  
	    public void setDesignunit(String designunit) {  
	        this.designunit = designunit;  
	    }  
	  
	    public String getCategoryName() {  
	        return categoryName;  
	    }  
	  
	    public void setCategoryName(String categoryName) {  
	        this.categoryName = categoryName;  
	    }  
	  
	    public String getSubcategoryName() {  
	        return subcategoryName;  
	    }  
	  
	    public void setSubcategoryName(String subcategoryName) {  
	        this.subcategoryName = subcategoryName;  
	    }  
	  
	    public String getProjectphaseName() {  
	        return projectphaseName;  
	    }  
	  
	    public void setProjectphaseName(String projectphaseName) {  
	        this.projectphaseName = projectphaseName;  
	    }  
	  
	    public String getProUse() {  
	        return proUse;  
	    }  
	  
	    public void setProUse(String proUse) {  
	        this.proUse = proUse;  
	    }  
	  
	    public String getStoreyHeight() {  
	        return storeyHeight;  
	    }  
	  
	    public void setStoreyHeight(String storeyHeight) {  
	        this.storeyHeight = storeyHeight;  
	    }  
	  
	    public Double getStoreyNum() {  
	        return storeyNum;  
	    }  
	  
	    public void setStoreyNum(Double storeyNum) {  
	        this.storeyNum = storeyNum;  
	    }  
	  
	    public Double getEavesHeight() {  
	        return eavesHeight;  
	    }  
	  
	    public void setEavesHeight(Double eavesHeight) {  
	        this.eavesHeight = eavesHeight;  
	    }  
	  
	    public String getStartWorkDate() {  
	        return startWorkDate;  
	    }  
	  
	    public void setStartWorkDate(String startWorkDate) {  
	        this.startWorkDate = startWorkDate;  
	    }  
	  
	    public String getEndWorkDate() {  
	        return endWorkDate;  
	    }  
	  
	    public void setEndWorkDate(String endWorkDate) {  
	        this.endWorkDate = endWorkDate;  
	    }  
	  
	    public String getStructureType() {  
	        return structureType;  
	    }  
	  
	    public void setStructureType(String structureType) {  
	        this.structureType = structureType;  
	    }  
	  
	    public String getSeismicIntensity() {  
	        return seismicIntensity;  
	    }  
	  
	    public void setSeismicIntensity(String seismicIntensity) {  
	        this.seismicIntensity = seismicIntensity;  
	    }  
	  
	    public String getDecorateStandard() {  
	        return decorateStandard;  
	    }  
	  
	    public void setDecorateStandard(String decorateStandard) {  
	        this.decorateStandard = decorateStandard;  
	    }  
	  
	    public Double getConstructionArea() {  
	        return constructionArea;  
	    }  
	  
	    public void setConstructionArea(Double constructionArea) {  
	        this.constructionArea = constructionArea;  
	    }  
	  
	    public Double getUndergroundArea() {  
	        return undergroundArea;  
	    }  
	  
	    public void setUndergroundArea(Double undergroundArea) {  
	        this.undergroundArea = undergroundArea;  
	    }  
	  
	    public Double getOvergroundArea() {  
	        return overgroundArea;  
	    }  
	  
	    public void setOvergroundArea(Double overgroundArea) {  
	        this.overgroundArea = overgroundArea;  
	    }  
	  
	    public String getAreaUnit() {  
	        return areaUnit;  
	    }  
	  
	    public void setAreaUnit(String areaUnit) {  
	        this.areaUnit = areaUnit;  
	    }  
}
