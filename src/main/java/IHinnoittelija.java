public interface IHinnoittelija {
	public abstract float getAlennusProsentti(Asiakas asiakas, Tuote tuote);

	public abstract void aloita();

	public abstract void setAlennusProsentti(Asiakas asiakas, float v);

	public abstract void lopeta();

}
