package de.tuberlin.av.openteagle.api.model;

public class VCT {

	public String id;
	public String commonName;
	public String description;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.commonName == null) ? 0 : this.commonName.hashCode());
		result = (prime * result)
				+ ((this.description == null) ? 0 : this.description.hashCode());
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final VCT other = (VCT) obj;
		if (this.commonName == null) {
			if (other.commonName != null) {
				return false;
			}
		} else if (!this.commonName.equals(other.commonName)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "VCT (ID: " + this.id + "): " + this.commonName + ". "
				+ this.description;
	}
}
