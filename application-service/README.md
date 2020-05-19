## Export the public key
Use the following command to export the public key from the generated JKS:
```bash
$ keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey -noout
```
## Create Public Key File
Copy&Paste the output from _-----BEGIN PUBLIC KEY-----_ till _-----END PUBLIC KEY-----_ in a file named _public.txt_
```bash
$ vi resource-service/src/main/resources/public.txt
```

## Testing
### Generating the token
```bash
$ curl -u clientId:secret -X POST localhost:9000/oauth/token\?grant_type=password\&username=user\&password=pass
```

#### Set Token Variable
- [fishshell](https://fishshell.com/)

    ```bash
    set token "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTQ3MDYwNzYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZGIwMThlZGYtMGZlNy00YzM1LTliNmQtYTBhODMyMTIzYTJiIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.q70Y3sPH4WUyBUze9v6g9tq8pN1pfx2PjBoufxK06aQ"
    ```
- bash
    ```bash
    token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTQ3MDYwNzYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZGIwMThlZGYtMGZlNy00YzM1LTliNmQtYTBhODMyMTIzYTJiIiwiY2xpZW50X2lkIjoiY2xpZW50SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.q70Y3sPH4WUyBUze9v6g9tq8pN1pfx2PjBoufxK06aQ"
    ```
    
### Accessing the resource
```bash
$ curl localhost:8080/api/whoami -H "Authorization: Bearer $token"
```


