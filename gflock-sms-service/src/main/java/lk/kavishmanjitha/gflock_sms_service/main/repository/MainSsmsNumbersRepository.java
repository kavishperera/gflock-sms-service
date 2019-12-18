package lk.kavishmanjitha.gflock_sms_service.main.repository;

import lk.kavishmanjitha.gflock_sms_service.main.entity.MainSsmsNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainSsmsNumbersRepository extends JpaRepository<MainSsmsNumbers, Integer> {

    List<MainSsmsNumbers> findBySmsStatus(boolean status);


    /*
    (2018-07-02) change
select
format(ifnull(sum(pos_t_transaction_details.final_value),0.00),2) as total_amount,
round(ifnull(sum(pos_t_transaction_details.item_qty),0.00),0) as item_count,
round(ifnull((
    select
        count(pos_t_transaction_summary.index_no)
    from
        pos_t_transaction_summary
    where
    pos_t_transaction_summary.tr_date = curdate()
    and
    pos_t_transaction_summary.tr_type = "invoice"
),0.00),0) as total_invoice_count,
round(ifnull((
    select
        sum(pos_t_transaction_details.item_qty)
    from
        pos_t_transaction_summary
    left join
        pos_t_transaction_details
    on
        pos_t_transaction_summary.index_no = pos_t_transaction_details.tr_index_no
    left join
        pos_m_item
    on
        pos_t_transaction_details.bar_code = pos_m_item.barcode
    where
        pos_t_transaction_summary.tr_date = curdate()
    and
        pos_t_transaction_details.tr_det_type = "Item"
    and
        pos_m_item.cost_center = "SHOES"
),0.00),0) as item_shoe_category_qty,
round(ifnull(
 sum(pos_t_transaction_details.final_value) /
 	(select
        count(pos_t_transaction_summary.index_no)
    from
        pos_t_transaction_summary
    where
    pos_t_transaction_summary.tr_date = curdate()
    and
    pos_t_transaction_summary.tr_type = "invoice") ,0.00),0) as avarage_income
from
pos_t_transaction_summary
left join
pos_t_transaction_details
on
pos_t_transaction_summary.index_no = pos_t_transaction_details.tr_index_no
where
pos_t_transaction_summary.tr_date = curdate()
and
pos_t_transaction_details.tr_det_type = "Item"
     */
    @Query(value = "select\n"
            + "format(ifnull(sum(pos_t_transaction_details.final_value),0.00),2) as total_amount,\n"
            + "round(ifnull(sum(pos_t_transaction_details.item_qty),0.00),0) as item_count,\n"
            + "round(ifnull((\n"
            + "    select\n"
            + "        count(pos_t_transaction_summary.index_no)\n"
            + "    from\n"
            + "        pos_t_transaction_summary\n"
            + "    where\n"
            + "    pos_t_transaction_summary.tr_date = curdate()\n"
            + "    and\n"
            + "    pos_t_transaction_summary.tr_type = \"invoice\"\n"
            + "),0.00),0) as total_invoice_count,\n"
            + "round(ifnull((\n"
            + "    select\n"
            + "        sum(pos_t_transaction_details.item_qty)\n"
            + "    from\n"
            + "        pos_t_transaction_summary\n"
            + "    left join\n"
            + "        pos_t_transaction_details\n"
            + "    on\n"
            + "        pos_t_transaction_summary.index_no = pos_t_transaction_details.tr_index_no\n"
            + "    left join\n"
            + "        pos_m_item\n"
            + "    on\n"
            + "        pos_t_transaction_details.bar_code = pos_m_item.barcode\n"
            + "    where\n"
            + "        pos_t_transaction_summary.tr_date = curdate()\n"
            + "    and\n"
            + "        pos_t_transaction_details.tr_det_type = \"Item\"\n"
            + "    and\n"
            + "        pos_m_item.cost_center = \"SHOES\"\n"
            + "),0.00),0) as item_shoe_category_qty,\n"
            + "round(ifnull(\n"
            + " sum(pos_t_transaction_details.final_value) /\n"
            + " 	(select\n"
            + "        count(pos_t_transaction_summary.index_no)\n"
            + "    from\n"
            + "        pos_t_transaction_summary\n"
            + "    where\n"
            + "    pos_t_transaction_summary.tr_date = curdate()\n"
            + "    and\n"
            + "    pos_t_transaction_summary.tr_type = \"invoice\") ,0.00),0) as avarage_income\n"
            + "from\n"
            + "pos_t_transaction_summary\n"
            + "left join\n"
            + "pos_t_transaction_details\n"
            + "on\n"
            + "pos_t_transaction_summary.index_no = pos_t_transaction_details.tr_index_no\n"
            + "where\n"
            + "pos_t_transaction_summary.tr_date = curdate()\n"
            + "and\n"
            + "pos_t_transaction_details.tr_det_type = \"Item\"", nativeQuery = true)
    public List<Object[]> getTotalIncomeAndTotalQty();
}
