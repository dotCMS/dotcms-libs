package com.tonicsystems.jarjar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Gamba
 *         Date: 11/6/13
 */
public class Dependency {

    private boolean generate;
    private boolean renameServices;
    private String path;

    private List<Ignore> toIgnore = new ArrayList<>();
    private List<Prefix> prefixes = new ArrayList<>();
    private List<ExtraRule> extraRules = new ArrayList<>();
    private List<CustomZap> customZaps = new ArrayList<>();

    public Dependency () {
    }

    public String getPath () {
        return this.path;
    }

    public void setPath ( String path ) {
        this.path = path;
    }

    public boolean isGenerate () {
        return generate;
    }

    public void setGenerate ( boolean generate ) {
        this.generate = generate;
    }

    public boolean isRenameServices () {
        return renameServices;
    }

    public void setRenameServices ( boolean renameServices ) {
        this.renameServices = renameServices;
    }

    public List<Ignore> getPackagesToIgnore () {
        return toIgnore;
    }

    public List<Prefix> getPrefixes () {
        return prefixes;
    }

    public List<ExtraRule> getExtraRules () {
        return extraRules;
    }

    public List<CustomZap> getCustomZaps () {
        return customZaps;
    }

    public Ignore createIgnore () {
        Ignore ignore = new Ignore();
        toIgnore.add( ignore );

        return ignore;
    }

    public Prefix createPrefix () {
        Prefix prefix = new Prefix();
        prefixes.add( prefix );

        return prefix;
    }

    public ExtraRule createExtraRule () {
        ExtraRule extraRule = new ExtraRule();
        extraRules.add( extraRule );

        return extraRule;
    }

    public CustomZap createCustomZap () {
        CustomZap customZap = new CustomZap();
        customZaps.add(customZap);

        return customZap;
    }

    /**
     * Inner class to add the ability to add prefixes to the dependencies.
     */
    public class Prefix {

        private String name;
        private Boolean strict = true;

        public String getName () {
            return name;
        }

        public void setName ( String name ) {
            this.name = name;
        }

        public Boolean getStrict () {
            return strict;
        }

        public void setStrict ( Boolean strict ) {
            this.strict = strict;
        }
    }

    /**
     * Inner class that allows to specify packages to exclude for the repackaging process
     */
    public class Ignore {

        private String parentPackage;

        public String getParentPackage () {
            return parentPackage;
        }

        public void setParentPackage ( String parentPackage ) {
            this.parentPackage = parentPackage;
        }

    }

    /**
     * Inner class to add the ability to add extra rules for cases that are not handle by the generated ones.
     */
    public class ExtraRule {

        private String pattern;
        private String result;

        public String getPattern () {
            return pattern;
        }

        public void setPattern ( String pattern ) {
            this.pattern = pattern;
        }

        public String getResult () {
            return result;
        }

        public void setResult ( String result ) {
            this.result = result;
        }
    }

    /**
     * Inner class to add the ability to use Zap rules, a zap rule allow us to exclude content from a jar
     */
    public class CustomZap extends Zap {

        private String parent;

        public String getParent () {
            return parent;
        }

        public void setParent ( String parent ) {
            this.parent = parent;
        }

        @Override
        public String toString () {
            return getParent() + " > " + "[ " + getPattern() + " ]";
        }

    }

}