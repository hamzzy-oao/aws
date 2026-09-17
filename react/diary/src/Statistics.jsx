import { useEffect, useState } from "react";
import { useAuth } from "./AuthContext";
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, PieChart, Pie, Cell, Legend } from "recharts";

import "./CSS/Statistics.css";

const COLORS = ['#FFB6B9', '#FAE3D9', '#BBDED6', '#8AC6D1', '#B2E1E9', '#F8B195', '#C06C84', '#6C5B7B', '#355C7D'];

function Statistics() {
    const { accessToken } = useAuth();
    const [year, setYear] = useState(new Date().getFullYear());
    const [emotionStats, setEmotionStats] = useState([]);
    const [monthlyStats, setMonthlyStats] = useState([]);

    useEffect(() => {
        const fetchStats = async () => {
            const response = await fetch(`/api/diary/stats?year=${year}`, {
                headers: { Authorization: `Bearer ${accessToken}` }
            });
            const result = await response.json();
            setEmotionStats(result.emotionStats);
            setMonthlyStats(
                result.monthlyStats.map(m => ({ month: `${m.month}월`, count: m.count }))
            );
        };
        if (accessToken) fetchStats();
    }, [accessToken, year]);

    return (
        <main className="statistics-page">
        <section className="statistics-container">
            <div className="statistics-header">
                <span className="statistics-icon">📊</span>

                <div>
                    <h1>{year}년 나의 감정 통계</h1>
                    <p>일기 기록을 바탕으로 감정 흐름을 확인해보세요.</p>
                </div>

                <select
                    className="statistics-year-select"
                    value={year}
                    onChange={e =>
                        setYear(Number(e.target.value))
                    }
                >
                    {[0, 1, 2].map(i => {
                        const selectedYear =
                            new Date().getFullYear() - i;

                        return (
                            <option
                                key={selectedYear}
                                value={selectedYear}
                            >
                                {selectedYear}년
                            </option>
                        );
                    })}
                </select>
            </div>

            <div className="statistics-grid">
                <section className="statistics-chart-card">
                    <div className="statistics-chart-title">
                        <span>🗓️</span>

                        <div>
                            <h2>월별 작성 빈도</h2>
                            <p>월마다 작성한 일기 개수입니다.</p>
                        </div>
                    </div>

                    <div className="statistics-chart-box">
                        <ResponsiveContainer
                            width="100%"
                            height={300}
                        >
                            <BarChart data={monthlyStats}>
                                <XAxis
                                    dataKey="month"
                                    tick={{ fill: "#765849" }}
                                />

                                <YAxis
                                    allowDecimals={false}
                                    tick={{ fill: "#765849" }}
                                />

                                <Tooltip />

                                <Bar
                                    dataKey="count"
                                    name="작성 개수"
                                    fill="#b86444"
                                    radius={[8, 8, 0, 0]}
                                />
                            </BarChart>
                        </ResponsiveContainer>
                    </div>
                </section>

                <section className="statistics-chart-card">
                    <div className="statistics-chart-title">
                        <span>😊</span>

                        <div>
                            <h2>연간 감정 비율</h2>
                            <p>가장 자주 기록한 감정을 확인하세요.</p>
                        </div>
                    </div>

                    <div className="statistics-chart-box">
                        <ResponsiveContainer
                            width="100%"
                            height={300}
                        >
                            <PieChart>
                                <Pie
                                    data={emotionStats}
                                    dataKey="count"
                                    nameKey="emotionName"
                                    cx="50%"
                                    cy="45%"
                                    outerRadius={95}
                                    label
                                >
                                    {emotionStats.map(
                                        (entry, index) => (
                                            <Cell
                                                key={index}
                                                fill={
                                                    COLORS[
                                                        index %
                                                        COLORS.length
                                                    ]
                                                }
                                            />
                                        )
                                    )}
                                </Pie>

                                <Tooltip />
                                <Legend />
                            </PieChart>
                        </ResponsiveContainer>
                    </div>
                </section>
            </div>
        </section>
    </main>
    );
}

export default Statistics;