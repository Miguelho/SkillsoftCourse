package generics;

public class GenericsExample {

	public static void main(String[] args) {
		CacheString cacheString = new CacheString("Hola");
		
		CacheAny<String> cacheStringGeneric = new CacheAny<>("hola");
		
		CacheAny<Shirt> cacheShirtGeneric = new CacheAny<>();
		
		cacheShirtGeneric.get();
	}

	
	

	
	static class CacheAny <T>{
		private T t;
		
		public CacheAny(){}
		
		public CacheAny(T t) {
			super();
			this.t = t;
		}

		public void add(T any){
			this.t=any;
		}
		
		public T get(){
			return this.t;
		}
	}
	
	static class CacheString{
		private String message;

		public CacheString(String message) {
			super();
			this.message = message;
		}
		
		public void add(String message){
			this.message=message;
		}

		public String getMessage() {
			return message;
		}
		
		
		
	}

	class CacheShirt{
		private Shirt shirt;
		
		
		
		public CacheShirt(Shirt shirt) {
			super();
			this.shirt = shirt;
		}

		public void add (Shirt shirt){
			this.shirt=shirt;
		}

		public Shirt getShirt() {
			return shirt;
		}
		
		
		
	}
	
	class Shirt{}
}
