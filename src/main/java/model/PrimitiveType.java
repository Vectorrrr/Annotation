package model;

import java.lang.reflect.Field;

/**
 * Created by igladush on 14.03.16.
 */
public enum PrimitiveType {
    BYTE("Byte", "byte") {
        @Override
        public Byte convertToDefaultType(String defValue) {
            return Byte.valueOf(defValue);
        }
    },
    SHORT("Short", "short") {
        @Override
        public Short convertToDefaultType(String defValue) {
            return Short.valueOf(defValue);
        }
    },
    INTEGER("Integer", "int") {
        @Override
        public Integer convertToDefaultType(String value) {
            return Integer.valueOf(value);
        }
    },
    LONG("Long", "long") {
        @Override
        public Long convertToDefaultType(String value) {
            return Long.valueOf(value);
        }
    },
    FLOAT("Float", "float") {
        @Override
        public Float convertToDefaultType(String value) {
            return Float.valueOf(value);
        }
    },
    DOUBLE("Double", "double") {
        @Override
        public Double convertToDefaultType(String value) {
            return Double.valueOf(value);
        }
    },
    CHARACTER("Character", "char") {
        @Override
        public Character convertToDefaultType(String value) {
            return value.charAt(0);
        }
    },
    BOOLEAN("Boolean", "boolean") {
        @Override
        public Boolean convertToDefaultType(String value) {
            return Boolean.valueOf(value);
        }
    },
    STRING("String", "String") {
        @Override
        public String convertToDefaultType(String value) {
            return value;
        }
    };


    private String primitiveName;
    private String wrapName;

    PrimitiveType(String primitiveName, String wrapName) {
        this.primitiveName = primitiveName;
        this.wrapName = wrapName;
    }

    public abstract Object convertToDefaultType(String defValue);

    public boolean isThisType(Field s) {
        String type = s.getType().getSimpleName();
        return wrapName.equals(type) || primitiveName.equals(type);
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public String getWrapName() {
        return wrapName;
    }
}

