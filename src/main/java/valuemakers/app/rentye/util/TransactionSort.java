package valuemakers.app.rentye.util;

public enum TransactionSort {

    //value 0 in the database
    REVENUE {
        @Override
        public String getCode() {return "REV";}
        @Override
        public String getDescription() {return "Revenue";}
    },

    //value 1 in the database
    COST {
        @Override
        public String getCode() {return "TDC";}
        @Override
        public String getDescription() {return "Tax Deductible Cost";}
    },

    //value 2 in the database
    PURCHASE {
        @Override
        public String getCode() {return "PRC";}
        @Override
        public String getDescription() {return "Property Purchase Cost";}
    },

    //value 3 in the database
    EXCLUDED {
        @Override
        public String getCode() {return "EXC";}
        @Override
        public String getDescription() {return "Excluded";}
    };

    public String getCode() {return null;}
    public String getDescription() {return null;}
}