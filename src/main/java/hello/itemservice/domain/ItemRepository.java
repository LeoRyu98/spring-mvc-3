package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){ // List 로 감싼 이유는 이후에 추가 데이터를 넣어도 Map 에 영향이 생기지 않기위함
        return new ArrayList<>(store.values());
    }

    /**
     * 실무에서는 Item 그대로 사용하는 것보다 사용 하지 않는 itemId 를 제외한 ItemUpdateDto 를 하나 새로 만드는 것이 좋다.
     * (명확성)
     */
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
