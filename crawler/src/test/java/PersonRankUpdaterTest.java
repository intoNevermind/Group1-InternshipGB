import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ferney on 22.11.17.
 */
public class PersonRankUpdaterTest {

    PersonRankUpdater personRankUpdater = new PersonRankUpdater();
    String testPage = "Сайт без Путина невозможно представить. Хотя не все демонстрируют уважение и граммотность. Как только не пишут: Путин, Путин; Путин- и даже Путин. ";
    String keyWord = "Путин";


    @Test
    public void testCountKeyRangs(){
       Assert.assertEquals(4, personRankUpdater.countKeyRangs(keyWord,testPage));
    }

}
