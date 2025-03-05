import { useState } from "react";
import trashCan from '/assets/icons/bin.svg';
import swap from '/assets/icons/swap.svg';

const Activeparkpass = () => {
    const [showLicensePlate, setShowLicensePlate] = useState(false);

    // Sample data - this data would come from the DB on load, if any
    const data = [
        { id: 1, name: "John Doe", licensePlate: "ABC-123", code: "12345", time: "1:45:00" },
        { id: 2, name: "Jane Smith", licensePlate: "XYZ-789", code: "12345", time: "32:00" },
        { id: 3, name: "Alice Johnson", licensePlate: "DEF-456", code: "12345", time: "12:27" },
    ];

    const toggleDisplay = () => {
        setShowLicensePlate((prev) => !prev);
    };

    return (
        <section className="mt-7 py-3 px-2 bg-white rounded">
            <h3 className="text-lg font-semibold m-2 ml-4 text-left">Active Passes</h3>

            <table className=" w-full">
                <thead>
                    <tr className="border-b border-beige">
                    <th
                        className=" p-2 text-left font-normal border-b border-beige cursor-pointer"
                        onClick={toggleDisplay}
                    >
                        <div className="flex items-center">
                            Name <img src={swap} alt="Swap" className="h-3 mx-1" /> Plate
                        </div>
                    </th>
                        <th className=" p-2 text-left font-normal border-b border-beige">Access Code</th>
                        <th className=" p-2 text-left font-normal border-b border-beige">Time</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((pass) => (
                        <tr key={pass.id} className="border-b border-beige">
                            <td className=" p-2 text-left border-r border-beige">
                                {showLicensePlate ? pass.licensePlate : pass.name}
                            </td>
                            <td className=" p-2 text-left border-r border-beige">{pass.code}</td>
                            <td className=" p-2 text-left">{pass.time}</td>
                            <td className=" p-2">
                                <div className="flex justify-end">
                                    <img src={trashCan} alt="Bin" className="h-5"/>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <p className="m-7 text-charcoal italic">Contact management if more than 3 guest passes are needed.</p>
        </section>
    );
};

export default Activeparkpass;