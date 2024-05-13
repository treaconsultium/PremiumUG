package com.dailycode.premiumug.reports.repository;


import com.dailycode.premiumug.reports.model.PortfolioInterfaceJ;
import com.dailycode.premiumug.reports.model.PremiumTransInterface;
import com.dailycode.premiumug.reports.model.PremiumsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PremiumsRepo extends JpaRepository<PremiumsModel,Integer> {

    @Query(value = "select * from (select distinct a.rec_id as RecId,a.countryid as CountryId, a.policyholderid as PolicyHolderId,b.entitytypedesc as EntitytypeDesc,\n" +
            "            a.entryline as EntryLine, a.entrylinedesc as EntryLineDesc, a.accountcode as AccountCode,b.payment_group as PayerGroup, \n" +
            "            b.firstname as Firstname,b.surname as Surname,b.companyname as CompanyName,b.externallymastered as ExternallyMastered, \n" +
            "            b.countrycode as CountryCode,coalesce(b.city,'Nairobi') as City, b.address1 as Address1, b.address2 as Address2 \n" +
            "            ,coalesce(b.postcode,'00100') as PostCode,b.country as Country,\n" +
            "            a.postingdate as PostingDate, a.journalid as JournalId, a.journaldesc as JournalDesc, \n" +
            "            a.currencyname as CurrencyName, a.policynumber as PolicyNumber, a.accountname as AccountName, \n" +
            "            a.broked as Broked, a.accountid as AccountId, a.sourcecompany as SourceCompany, a.amount as Amount,\n" +
            "            a.gross_amount as GrossAmount,a.intermediaryid as IntermediaryId, a.Intermediaryname as IntermediaryName,\n" +
            "            coalesce(a.branchcode,'101') as Branchcode, a.branchname as BranchName, b.entityid as EntityId,b.entitytype as EntityType,\n" +
            "            case when a.countryid = 'UG' and a.policynumber like 'UIND%' and b.entitytype =2 and b.pin_no is null then b.id_no " +
            "                 when a.countryid = 'UG' and a.policynumber like 'JSIND%' and b.entitytype =2 and b.pin_no is null then b.id_no " +
            "                 when a.countryid = 'UG' and a.policynumber like 'UJA%' and b.entitytype =2 and b.pin_no is null then b.id_no\n" +
            "                 else \n" +
            "                       case when a.countryid = 'UG' and LEN(b.pin_no) = 10 and SUBSTRING(b.pin_no,1,2) = '10' then b.pin_no \n" +
            "                             else '' end\n" +
            "            end as PinNo, \n" +
            "            b.entityname as EntityName, b.county as County,a.portfolio as Portfolio,a.ProductId as ProductId \n" +
            "            from DIGITAL_APPS.dbo.OFAPremiums a \n" +
            "            inner join DIGITAL_APPS.dbo.OFAEntities b \n" +
            "            on a.POLICYHOLDERID  = b.ENTITYID \n" +
            "            where b.payment_group = 'MEDICAL-CLAIM' \n" +
            "            and b.countrycode = 16 \n" +
            "            and a.journalid in :part\n" +
            "            and a.countryid = 'UG' \n" +
            "            group by a.rec_id,a.journalid,a.countryid,a.policyholderid,a.entryline,a.entrylinedesc,a.accountcode,\n" +
            "            a.postingdate,a.journaldesc,a.currencyname,a.policynumber,a.accountname,a.broked,a.accountid,\n" +
            "            a.sourcecompany,a.amount,a.gross_amount,a.intermediaryid,a.Intermediaryname,a.branchcode,\n" +
            "            a.branchname,b.entityid,b.entitytype,b.pin_no,b.id_no,b.entityname,b.county,a.portfolio,b.entitytypedesc, \n" +
            "            b.payment_group,b.countrycode,b.city,b.address1,b.address2,b.postcode,b.country,b.firstname, \n" +
            "            b.surname,b.companyname,b.externallymastered,a.ProductId ) q" +
            "     where q.PinNo <> '' " +
            "     order by q.JournalId,q.PostingDate,q.EntityId"
            , nativeQuery = true)
    List<PremiumTransInterface> fetchAllTransactions(@Param("part") List<String> part);


    @Query(value = "select distinct  a.journalid as journalId \n" +
            "from DIGITAL_APPS.dbo.OFAPremiums a \n" +
            "where " +
//            "a.journalid = '111581839' " +
            "(cast(a.POSTINGDATE as date) between '2024-03-01' and '2024-03-31') " +
            "and a.picked_status =0 \n " +
            "and " +
            "a.countryid = 'UG' " +
            "and a.currencyname = 'UGX' \n" +
            "and a.gross_amount <> 0 " +
            "and a.entrylinedesc <> 'MED EXCESS' "
            , nativeQuery = true)
    List<String> getReadyPremiums();

    @Query(value = "select top 1 PolicyId,EffectiveDate,ProductId, \n" +
            "case when ProductId in (52,45,51,55,58,50,53,6,38,39,64,35,25,44,54,67,66,17,70) then '52' \n" +
            "     when ProductId in (7,34,36,47,59,60,61,65,18) then '53' \n" +
            "     when ProductId in (13,15,62,63,23,20) then '51' \n" +
            " else '' end as Portfolio," +
            "PolicyHolderId\n" +
            "from PR_ACTJUB.dbo.policy where PolicyHolderId =:entityId \n" +
            "and PolicyStatus ='L' and PaymentMethod <> ''", nativeQuery = true)
    Optional<PortfolioInterfaceJ> fetchPortfolio(@Param("entityId") String entityId);

}
