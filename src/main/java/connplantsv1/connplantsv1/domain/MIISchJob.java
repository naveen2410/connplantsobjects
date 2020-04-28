package connplantsv1.connplantsv1.domain;

public class MIISchJob {

	public String site;
	public String shoporder;
	public String item;
	public String qty_to_build;
	public String qty_done;
	public String qty_scrapped;
	public String modified_dt;

	public MIISchJob(){}

	public MIISchJob(String site, String shoporder, String item, String qty_to_build, String qty_done,
			String qty_scrapped, String modified_dt) {
		super();
		this.site = site;
		this.shoporder = shoporder;
		this.item = item;
		this.qty_to_build = qty_to_build;
		this.qty_done = qty_done;
		this.qty_scrapped = qty_scrapped;
		this.modified_dt = modified_dt;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getShoporder() {
		return shoporder;
	}

	public void setShoporder(String shoporder) {
		this.shoporder = shoporder;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQty_to_build() {
		return qty_to_build;
	}

	public void setQty_to_build(String qty_to_build) {
		this.qty_to_build = qty_to_build;
	}

	public String getQty_done() {
		return qty_done;
	}

	public void setQty_done(String qty_done) {
		this.qty_done = qty_done;
	}

	public String getQty_scrapped() {
		return qty_scrapped;
	}

	public void setQty_scrapped(String qty_scrapped) {
		this.qty_scrapped = qty_scrapped;
	}

	public String getModified_dt() {
		return modified_dt;
	}

	public void setModified_dt(String modified_dt) {
		this.modified_dt = modified_dt;
	}




}
