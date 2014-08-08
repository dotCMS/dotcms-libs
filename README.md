# Naming convention for jars inside the dotcms-libs repository

All the jars inside this repository must have this naming convention otherwise the repackaging and artifactory uploading of jars wont work properly.

The name convention to use is: jar name + **_** + jar version, please note the use of the **_** the underscore is a reserved symbol and must be present **only** as a version divisor.

Examples:

```
concurrent_1.3.4.jar
chemistry-opencmis-server-bindings_0.8.0.jar
chemistry-commons_0.5-SNAPSHOT.jar
elasticsearch_0.90.3.jar
```

The version **must** be always present, even if the version of the jar is unknown, in that case the **ukv** word must be use it as a version:

```
daisydiff_ukv.jar
```


# Using the build.xml

In order to repackage the jars on this repository run: 

```
ant repackage
```

That ant task will repackage all the jars configured on the *build.xml* applying defined specified rules and saving the resulting jars inside the **./build** folder.

# Uploading jars to artifactory

In order to upload files to artifactory exist the **build.gradle** script, that script will upload all the jars found under the **./build** folder to the **http://repo.dotcms.com/artifactory/ext-release-local** repository, only jars inside the *./build* folder and with the proper name convention (`dot.*.jar`) are allowed to be uploaded to artifactory, meaning only repackaged jars should be uploaded to the **ext-release-local** repository.

The **build.gradle** in the process of uploading each repackaged jar will append a custom dotcms version to the jar to upload, that version will be incremented each time that jar is modified by the repackaging process starting by the version **1**.

Examples:

Initial jar
```
concurrent_1.3.4.jar
```

Jar after repackage process
```
dot.concurrent_1.3.4.jar
```

With custom dotcms version given by the **build.gradle** script
```
dot.concurrent_1.3.4_1.jar
```

The **build.gradle** before upload the jar will verify if it was already uploaded, if not it will assume the custom dotcms version is **1** if it was already uploaded will search and find the next available custom version available to use.

### Using the script


* First you must modify the authentication data required to upload files to artifactory:
 * build.gradle -> Line 10: ext.username = 'user.name'
 * build.gradle -> Line 11: ext.password = 'XYZ'
  
* run: `gradle uploadArchives` 
