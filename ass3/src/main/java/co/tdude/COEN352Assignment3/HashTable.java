package co.tdude.COEN352Assignment3;

public class HashTable<K extends Comparable<? super K>, E extends Comparable<E>> {
    // We don't actually care if K is Comparable, we only do it for consistency
    private int M;
    private KVpair<K,E>[] T;
    private KVpair<K,E> TombStone = new KVpair<K,E>(); // Tombstone flyweight

    private int sfold(String x, int M) {
        char ch[];
        ch = x.toCharArray();
        int intlength=x.length()/4;
        long sum=0;
        int count=0;
        for(int i=0;i<intlength;i++) {
            sum+=(((long)ch[count])<<24) + (((long)ch[count+1])<<16) +
                    (((long)ch[count+2])<<8) + ((long)ch[count+3]);
            count+=4;
        }
        return(Math.abs((int)sum) % M);
    }

    /** If the key is an Integer, then use a simple mod function for
     the hash function. If the key is a String, then use folding. */
    private int h(K key) {
        Object keyO = (Object)key;
        if (keyO.getClass() == Integer.class)
            return (Integer)keyO % M;
        else if (keyO.getClass() == String.class)
            return sfold((String)keyO, M);
        else
            return key.hashCode() % M;
    }

    /** Implement collision resolution with linear probing */
    private int p(K key, int slot) {
        return slot;
    }

    @SuppressWarnings("unchecked")  // Allow the generic array allocation
    HashTable(int m) {
        M = m;
        T = (KVpair<K,E>[])new KVpair[M];
    }

    /** Insert record r into T */
    // Bugged for now -- this skips tombstones
    public void hashInsert(K k, E r) {
        int home;                          // Home position for r
        int pos = home = h(k);             // Initial position
        for (int i=1; T[pos] != null && T[pos] != TombStone; i++) {
            assert T[pos].key().compareTo(k) != 0 : "Duplicates not allowed";
            pos = (home + p(k, i)) % M;      // Next probe slot
        }
        T[pos] = new KVpair<K,E>(k, r);    // Insert R
    }

    /** Search for the record with key k */
    E hashSearch(K k) {
        int home;              // Home position for k
        int pos = home = h(k); // Initial position
        for (int i = 1; (T[pos] != null) &&
                (T[pos] == TombStone || T[pos].key().compareTo(k) != 0); i++)
            pos = (home + p(k, i)) % M;    // Next probe position
        if (T[pos] == null) return null; // K not in hash table
        else return T[pos].value();              // Found it
    }

    /** Remove a record with key value k from the hash table */
    E hashRemove(K k) {
        int home;              // Home position for k
        int pos = home = h(k); // Initial position
        for (int i = 1; (T[pos] != null) &&
                (T[pos] == TombStone || T[pos].key().compareTo(k) != 0); i++)
            pos = (home + p(k, i)) % M;    // Next probe position
        if (T[pos] == null) return null; // K not in hash table
        else { // Found it
            E e = T[pos].value();
            T[pos] = TombStone;
            return e;
        }
    }

    /** Remove some record from the hash table */
    E hashRemoveAny() {
        int i;
        for (i=0; i<M; i++)
            if (T[i] != null && T[i] != TombStone) break;
        E e = T[i].value();
        T[i] = TombStone;
        return e;
    }
}
