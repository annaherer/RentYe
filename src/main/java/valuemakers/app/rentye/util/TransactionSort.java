package valuemakers.app.rentye.util;

public enum TransactionSort {
    REVENUE {
        @Override
        public String getCode() {return "REV";}
        @Override
        public String getDescription() {return "Revenue";}
    },

    COST {
        @Override
        public String getCode() {return "TDC";}
        @Override
        public String getDescription() {return "Tax Deductible Cost";}
    },

    PURCHASE {
        @Override
        public String getCode() {return "PRC";}
        @Override
        public String getDescription() {return "Property Purchase Cost";}
    },

    EXCLUDED {
        @Override
        public String getCode() {return "EXC";}
        @Override
        public String getDescription() {return "Excluded";}
    };

    public String getCode() {return null;}
    public String getDescription() {return null;}
}