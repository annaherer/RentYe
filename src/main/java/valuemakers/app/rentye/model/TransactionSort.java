package valuemakers.app.rentye.model;

public enum TransactionSort {
    REVENUE {
        public String getCode() {return "REV";}
        public String getDescription() {return "Revenue";}
    },

    COST {
        public String getCode() {return "TDC";}
        public String getDescription() {return "Tax Deductible Cost";}
    },

    PURCHASE {
        public String getCode() {return "PRC";}
        public String getDescription() {return "Property Purchase Cost";}
    },

    EXCLUDED {
        public String getCode() {return "EXC";}
        public String getDescription() {return "Excluded";}
    };

    public String getCode() {return null;}
    public String getDescription() {return null;}
}