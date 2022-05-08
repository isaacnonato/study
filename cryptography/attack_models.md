# Attack Models
The following attack models can be used by attackers:

* __Ciphertext-only attackers (COA)__: The less secure model, the attacker can observe ciphertexts but not send encryption or decryption queries.
* __Known-plaintext attackers (KPA)__: The attacker can observe the ciphertexts and their respective plaintexts. It's still a passive attack model, since the attacker still can't perform encryption/decryption queries.
* __Chosen-plaintext attackers (CPA)__: Can perform encryption queries for any plaintexts and observe their respective ciphertexts. Unlike the previous two, CPA is an active attack model.
* __Chosen-cypertext attackers (CCA)__: The strongest attack model, the attacker can perform both encryption and decryption queries.

# Security Goals

* __Indistinguishability (IND)__: Ciphertexts should be indistinguishable from one another. That means if an attacker picks two plaintexts and then receives a ciphertexts from one of them (randomly selected) the attacker mustn't be able to distinguish from wish plaintext the ciphertext came from. 

* __Non-malleability (NM)__: Ciphertexts shouldn't be related to each other in any meaningful way. For example, given a cipher C<sub>1</sub> = E(K, P<sub>1</sub>), it must be impossible to create another cipher C<sub>2</sub> (that takes a plaintext P<sub>2</sub>), that is in some way related to P<sub>1</sub> (for example, create a P<sub>2</sub> that is equal to P<sub>1</sub> ⊕ X, for some known value X). 

## Semantic Security and Randomized Encryption (IND-CPA)

The IND-CPA, also called *semantic security and randomized encryption* is the most important security notion out there. It means that a ciphertexts shouldn't leak any information about plaintext as long as the key it secret. That means encryption should return different ciphertexts even if the plaintexts are the same. 

### Randomized encryption

The best way to achieve IND-CPA is using randomized encryption. It randomizes the encryption process and returns different ciphertexts even if the same plaintext is encrypted twice; otherwise an attacker could easily identify duplicate plaintexts from their ciphertexts, contradicting the security notion that ciphertexts shouldn't reveal any information.

Let's consider an example where the encryption isn't randomized. As we know from the IND definition above, the attacker have access to two plaintexts (P<sub>1</sub> and P<sub>2</sub>) and a ciphertext C<sub>i</sub>, where i can be either 1 or 2. Since the attacker can perform encryption queries (see the CPA definition above). He can just perform the encryption C<sub>1</sub> = __E__(K, P<sub>1</sub>) and C<sub>2</sub> = __E__(K, P<sub>2</sub>), remember that in the CPA attack model the attacker has access to the plaintexts. After that he just needs to compare whether C<sub>i</sub> is equal to C<sub>1</sub> or C<sub>2</sub>.

## Achieving Semantically Secure Encryption

The best (and simpler) construction for a semantically secure cipher uses a*deterministic random bit generator*, an algorithm that returns random-looking bits given a secret value:

<center markdown="1">E(K, R, P) = (<strong>DRBG</strong>(K || R) ⊕ P, R) </center>

Where R is randomly chosen for each new encryption and given to a DRBG along with the key (K || R refers to the string K followed by R).
