# Types of Encryption 

## Asymmetric Encryption

In asymmetric encryption, there are two keys: a public and a private. The public key is the encryption key and it's publicy available to anyone who wants to send you encrypted messages. The public key can be computed from the private key, but not vice-versa.

## Authenticated Encryption

Authenticated Encryption (referred as _AE_) is a type of symmetric encryption that returns an _authentication tag_ with the ciphertext:

<center> <b>AE</b>(K, P) = (C, T)</center>

The authentication that T is a short string that's impossible to guess without the key. The decryption method only returns P if it verifies that T is a valif tag for the pair.

### Authenticated encryption with associated data (AEAD)

AEAD is an extension of authenticated encryption that takes some cleartext and unencrypted data and uses it to generate the __AEAD__(K, P, A) = (C, T) authentication tag. AEAD is usually used to protect protocols' datagrams with a cleartext header and unencrypted payload.

## Format-preserving encryption

As the name says, it creates ciphertexts that have the same format as the plaintext. May be required in databases that can only record data in a prescribed format.
FPE can encrypt IP addresses to IP addresses, ZIP codes to ZIP codes, and so on.

## Fully homomorphic encryption

FHE enables uses to replace a ciphertext C = __E__(K, P) with another ciphertext
C' = __E__(K, __F__(P)), where __F__(P) can be any function of P, and without ever decrypting the initial ciphertext C.
