package ua.prozorro.prozorro.model.contracts;

import com.google.gson.annotations.SerializedName;

public class PlanData extends ua.prozorro.prozorro.model.plans.PlanData {
    @SerializedName("data")
    private Contract contract;

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContractData{");
        sb.append("contract=").append(contract);
        sb.append('}');
        return sb.toString();
    }
}
