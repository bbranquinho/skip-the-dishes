# Skip the Dishes Security Application

Commands used to generate certs.

```bash
$ keytool -genkeypair -alias skipdishes -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass skipdishes -keystore skipdishes.jks -storepass skipdishes -deststoretype pkcs12
$ keytool -export -keystore skipdishes.jks -alias skipdishes -file skipdishes.cer
```
