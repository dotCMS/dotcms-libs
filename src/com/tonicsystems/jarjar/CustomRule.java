package com.tonicsystems.jarjar;

import java.util.Comparator;

/**
 * @author Jonathan Gamba
 *         Date: 12/11/13
 */
public class CustomRule extends Rule {

    private String parent;
    private boolean extra;
    
    public String getParent () {
        return parent;
    }

    public void setParent ( String parent ) {
        this.parent = parent;
    }
    
    public void setExtra(boolean extra){
    	this.extra = extra;
    }
    
    public boolean isExtra(){
    	return extra;
    }
    
    @Override
    public String toString(){
    	return parent + " > " + "[ " + getPattern() + " => " + getResult() + " ]" + (isExtra()?"E":"");  
    }

    public class RuleSortByParent implements Comparator<CustomRule> {

        private String currentjar;

        public RuleSortByParent ( String curentjar ) {
            this.currentjar = curentjar;
        }

        /**
         * This compare method put at the beginning the rules that have
         * as parent the current jar, the Nulls ones at the bottom and 
         * everything else is equal
         */
        @Override
        public int compare ( CustomRule rule1, CustomRule rule2 ) {
        	if ( rule1.getParent().equals( rule2.getParent()) ) {
        		if(rule1.isExtra() && rule2.isExtra())
        			return 0;
        		if(rule1.isExtra())
        			return -1;
        		if(rule2.isExtra())
        			return 1;
                return 0;
            } else if ( !rule1.getParent().equals( rule2.getParent() )){
            	if( rule1.getParent().equals( currentjar )) {
            		return -1;
            	}else if( rule2.getParent().equals( currentjar )) {
            		return 1;
            	}else if(rule1.getParent()==null){
            		return 1;
            	}else if(rule2.getParent()==null){
            		return -1;
            	}
            }
            return 0;
        }
    }

}