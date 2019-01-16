On ERROR:
javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target

SOLUTION:
First you can check if your certificate is already in the keystore by running the following command: keytool -list -keystore "%JAVA_HOME%/jre/lib/security/cacerts" (you don't need to provide a password)
If your certificate is missing you can get it by downloading it with your browser and add it to the keystore with the following command:
keytool -import -noprompt -trustcacerts -alias <AliasName> -file   <certificate> -keystore <KeystoreFile> -storepass <Password>
Example:
FOR JDK(for developing):
keytool -import -noprompt -trustcacerts -alias public.api.openprocurement.org -file d:/1/public.api.openprocurement.org.cer -keystore "%JAVA_HOME%/jdk/lib/security/cacerts" -storepass changeit

FOR JRE(client only):
keytool -import -noprompt -trustcacerts -alias public.api.openprocurement.org -file d:/1/public.api.openprocurement.org.cer -keystore "C:\Program Files\Java\jre1.8.0_121\lib\security\cacerts" -storepass changeit


Work with JSON online: https://jsoneditoronline.org/
                        http://jsonparseronline.com/

Start page: 
https://public.api.openprocurement.org/api/<VERSION>/tenders
https://public.api.openprocurement.org/api/<VERSION>/plans


Get Tender: https://public.api.openprocurement.org/api/<VERSION>/tenders/<TenderID>
Example TENDERID: 67403dc828034b2eb3c12b40674da3ad

Good example:  https://public.api.openprocurement.org/api/<VERSION>/tenders/55e20be09f3544deace53f5a9019f800

Tested for <VERSION> = 2.4

Create classes from JSON: http://www.jsonschema2pojo.org/