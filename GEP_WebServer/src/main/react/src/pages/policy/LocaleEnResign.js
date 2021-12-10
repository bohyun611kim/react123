import React from 'react'

export default function LocaleEnResign() {
    return (
    <div className="terms">
        <p>Please check the following information before canceling your membership.</p>
        <h4>1. Reason why membership cancellation is not allowed</h4>
        <p>If one or more of the items below are applicable, then membership cancellation is not possible. (This can be reviewed in the next stage.)</p>
        <ol className="alpha">
            <li>If there are ongoing transactions</li>
            <li>If the remaining assets held are at or above KRW 10,000</li>
            <li>If there are ongoing cryptocurrency deposits or withdrawals</li>
            <li>If there are paid services being used</li>
            <li>If there are paid services that have not been used</li>
            <li>If you are processing SMS wire transfer</li>
        </ol>
        <h4>2. Forfeit and no service</h4>
        <p>Please take special care as wrongly deposited cryptocurrencies to accounts that are already canceled are impossible to retrieve due to the characteristics (anonymity) of the blockchain. Paid and free services linked to the KRXC account may also no longer be used,and are automatically ceased. If you have an OTP card, it will be automatically discarded upon your membership cancellation and not be available for re-use.</p>
        <h4>3. Small asset disposal</h4>
        <p>The final small sum assets left over from withdrawal and transfers may be donated to the Korea Childhood Leukemia Foundation with the member's approval when membership is canceled as below. The rights to small sums that are not processable are relinquished.</p>
        <ol className="alpha">
            <li>Small sum assets when liquidating assets = minimum amount for withdrawal + KRW or cryptocurrencies less than the withdrawal fee</li>
            <li>Small sum assets when transferring assets = KRW or cryptocurrencies less than minimum amount for transfer</li>
        </ol>
        <h4>5. Storage of usage records</h4>
        <p>When a membership is canceled, all of the member's personal information registered with KRXC is deleted, destroyed. Once deleted, this information cannot be recovered.</p>
        <h4>4. Deletion of member information</h4>
        <p>However, if the personal information needs to be retained in accordance with the provisions of relevant statutes (such as commercial law and the Protection of Consumers in e-Commerce and Other Transactions Act), the company shall retain the personal information for the set period as mandated by the relevant statues. In this case, the company shall only keep the stored information for storage purpose.</p>
        <p>The storage periods are as below:</p>
        <ol className="alpha">
            <li>Records on contract or subscription cancellations
                <ul className="circle">
                    <li>Grounds for storage: Protection of Consumers in e-Commerce and Other Transactions Act</li>
                    <li>preservation period : 5 years</li>
                </ul>
            </li>
            <li>Records on price settlement and the supply of goods
                <ul className="circle">
                    <li>Grounds for storage: Protection of Consumers in e-Commerce and Other Transactions Act</li>
                    <li>preservation period : 5 years</li>
                </ul>
            </li>
            <li>Records on the handling of customers’ complaints and disputes
                <ul className="circle">
                    <li>Grounds for storage: Protection of Consumers in e-Commerce and Other Transactions Act</li>
                    <li>preservation period : 3 years</li>
                </ul>
            </li>
        </ol>
    </div>
    )
}
