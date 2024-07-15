package com.example.findingFlatmates.specifications;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.Specification;

import com.example.findingFlatmates.entities.flatPosts;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SearchSpecification {
	//Specification here is an interface which consists of a toPredicate Method which is being used here 
	//indirectly to create dynamic query
	public static Specification<flatPosts> withDynamicQuery(String city, String roomType, String tenenttype, Long price){
		/* Directly writing return(Root<flatPosts> root, CriteriaQuery<?> query, CriteriaBuilder cb)
		 * and starting the building process we can also write it
		 * 
		 * return new Specification<flatPosts>(){
		 * 		@Override
		 * 		public Predicate toPredicate(Root<flatPosts> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		 * 			List<Predicate> predicates = new ArrayList<>();
		 * 	}
		 * }
		 */
		
		
		
		return (Root<flatPosts> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			//This is city predicate
			if (city != null && !city.isEmpty()) {
				//Here we are using .equal to check if 
				//city field has the same input as the city that is being passed here.
                predicates.add(cb.equal(root.get("city"), city));
            }
			//This is room predicate
			if (roomType != null && !roomType.isEmpty()) {
                predicates.add(cb.equal(root.get("roomType"), roomType));
            }
			//This is tenent predicate
			if (tenenttype != null && !tenenttype.isEmpty()) {
                predicates.add(cb.equal(root.get("tenenttype"), tenenttype));
            }
			//This is a price predicate
			if (price != null && price > 0) {
                predicates.add(cb.equal(root.get("price"), price));
            }
			//Here we are using .and method to combine all predicates into one.
			//The list is being converted into array because JPA criteria Api excepts array of predicates.
			//And new Predicate[0] here size is 0 because whenever we use this method toArray
			//we do not define the size by ourselves the method will automatically find the appropriate
			//size and fill the contents in array this way neither the size becomes less and more.
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
