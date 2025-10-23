package com.blog.blogapp;

import java.lang.reflect.Field;
import java.util.Arrays;

public class EntityUtils {

	 public static boolean isValidField (Class<?> clazz, String FieldName) {
		 return Arrays.stream(clazz.getDeclaredFields())
				      .anyMatch(f->f.getName().equals(FieldName));
	 }
	 
	// ✅ Return a valid field, fallback to default if invalid or null
	 public static String getValidFieldOrDefault(Class<?> clazz, String fieldName, String defaultField) {
	        if (fieldName == null || fieldName.isBlank() || !isValidField(clazz, fieldName)) {
	            return defaultField;
	        }
	        return fieldName;
	    }
}
