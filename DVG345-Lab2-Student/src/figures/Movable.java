package figures;
/** Förmågan att förskjuta det befintliga objektet. */
public interface Movable
  {
  /** Bevarar form, storlek och identitet. Ogiltiga/icke-ändliga förskjutningar
   * eller koordinatöverflöd avvisas med IllegalArgumentException utan ändring.
   */
  void move(double dx, double dy);
  }
