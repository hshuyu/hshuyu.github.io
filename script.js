// 问题列表和表白配置
const config = {
    questions: [
        {
            text: '小🌲发现一只可爱的修🐕！要带回家吗？',
            options: ['假装没看见', '偷偷把小🌲带回家', '和修🐕一起修沟'],
            correct: 1
        },
        {
            text: '小🌲呆呆地在bubububu，这时你会？',
            options: ['？莫名其妙不管他', '家暴他！', '和小🌲xia嘟嘟嘟嘟~'],
            correct: 2
        },
        {
            text: '小🌲说他饿了，你应该怎么办？',
            options: ['给他吃🍬', '给他买奶茶', '和他一起玩游戏'],
            correct: 0
        },
        {
            text: '小小🌲突然说想你了，你最想怎么做？',
            options: ['欺负他！', '发现小🌲嘴巴干了', '我去洗澡了'],
            correct: 0
        }
    ],
    finalTexts: [
        '？',
        '你和修沟的修🐕去修沟吧！生气╬',
        '你到底在干什莫？>︿<',
        '~~>_<~~你不是我的小宝贝！',
        '捕获成功！❤ 从今往后你就是我的专属小可爱啦～'
    ]
};

// 初始化
let currentQuestion = 0;
let correctCount = 0;
const container = document.getElementById('question-container');
const resultDiv = document.getElementById('result');
const canvas = document.getElementById('fireworks');
const ctx = canvas.getContext('2d');

// 设置画布尺寸
function initCanvas() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
}

// 创建粒子效果
class Particle {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.size = Math.random() * 5 + 2;
        const angle = Math.PI * 2 * Math.random();
        this.speedX = Math.cos(angle) * (3 + Math.random() * 2);
        this.speedY = Math.sin(angle) * (3 + Math.random() * 2) - 8;
        this.color = `hsla(340, 100%, 85%, ${Math.random()*0.5+0.5})`;
    }

    update() {
        this.x += this.speedX;
        this.y += this.speedY;
        this.speedY += 0.3;
    }

    draw() {
        ctx.fillStyle = this.color;
        ctx.beginPath();
        // 动态切换爱心路径
            const heartPath = new Path2D();
            const isBroken = this.color.includes('220');
            const basePath = isBroken ? 
                `M${this.x-this.size},${this.y+this.size} C${this.x-this.size*2},${this.y-this.size*3} ${this.x+this.size*2},${this.y-this.size*3} ${this.x+this.size},${this.y+this.size} ` +
                `C${this.x+this.size*2},${this.y-this.size*6} ${this.x+this.size*4},${this.y-this.size*5} ${this.x+this.size*3},${this.y+this.size} ` +
                `L${this.x},${this.y+this.size*3} L${this.x-this.size*2},${this.y+this.size}` :
                `M${this.x-this.size},${this.y+this.size} C${this.x-this.size*2},${this.y-this.size*3} ${this.x+this.size*2},${this.y-this.size*3} ${this.x+this.size},${this.y+this.size} ` +
                `C${this.x+this.size*2},${this.y-this.size*3} ${this.x+this.size*4},${this.y-this.size*3} ${this.x+this.size*3},${this.y+this.size} ` +
                `L${this.x},${this.y+this.size*3} Z`;
            
            heartPath.addPath(new Path2D(basePath));
            ctx.fill(heartPath);
        ctx.fill();
    }
}

// 显示问题
function showQuestion() {
    const question = config.questions[currentQuestion];
    document.getElementById('question').textContent = question.text;
    
    const optionsDiv = document.getElementById('options');
    optionsDiv.innerHTML = '';
    
    question.options.forEach((option, index) => {
        const btn = document.createElement('button');
        btn.textContent = option;
        btn.onclick = () => handleAnswer(index);
        btn.ontouchstart = () => handleAnswer(index);
        optionsDiv.appendChild(btn);
    });
}

// 处理答案
function handleAnswer(selectedIndex) {
    const correctIndex = config.questions[currentQuestion].correct;
    
    currentQuestion++;
    
    if (selectedIndex === correctIndex) {
        correctCount++;
    }
    
    if (currentQuestion < config.questions.length) {
        showQuestion();
    } else {
        showFinal(correctCount);
    }
    
    if (selectedIndex !== correctIndex) {
        createHearts('#70a1ff', true);
        document.querySelectorAll('button')[selectedIndex].animate([
            { transform: 'translateX(0px) scale(1)', background: 'rgba(255,255,255,0.9)' },
            { transform: 'translateX(-30px) scale(0.95)', background: '#ff4757' },
            { transform: 'translateX(30px) scale(0.95)', background: '#ff4757' },
            { transform: 'translateX(0px) scale(1)', background: 'rgba(255,255,255,0.9)' }
        ], {
            duration: 600,
            easing: 'cubic-bezier(0.25, 0.46, 0.45, 0.94)',
            iterations: 1
        });
    }
}

// 显示最终表白
function showFinal(score) {
    const finalIndex = Math.min(config.finalTexts.length - 1, Math.max(0, score));
    const finalText = config.finalTexts[finalIndex];
    
    // 根据正确率触发不同特效
    if(finalIndex === config.finalTexts.length - 1) {
        createFireworks();
    } else if(finalIndex >= 3) {
        createHearts('#70a1ff');
    } else if(finalIndex >= 1) {
        createHearts('#ff6b6b');
    }
    
    container.classList.add('hidden');
    resultDiv.classList.remove('hidden');
    initCanvas();
    typeWriter(finalText);
}

// 打字机效果
function typeWriter(text) {
    const element = document.getElementById('typing-effect');
    let index = 0;
    
    function type() {
        if (index < text.length) {
            element.innerHTML += text.charAt(index);
            index++;
            setTimeout(type, 100);
        }
    }
    type();
}

// 创建爱心粒子
function createHearts(color = '#ff4757', isBroken = true) {
    const heartColor = color;
    for (let i = 0; i < 15; i++) {
        const heart = document.createElement('div');
        heart.style.cssText = `
            position: absolute;
            left: ${Math.random()*100}%;
            top: ${Math.random()*100}%;
            font-size: ${Math.random()*30 + 20}px;
            color: ${heartColor};
            animation: brokenHeart 1s ease-out;
            opacity: 0;
        `;
        heart.innerHTML = '❤';
        document.body.appendChild(heart);
        
        heart.animate([
            { opacity: 1, transform: 'translateY(0)' },
            { opacity: 0, transform: 'translateY(-100px) rotate(45deg) scale(0.5)' }
        ], {
            duration: 1500,
            easing: 'ease-out'
        }).onfinish = () => heart.remove();
    }
}

// 创建烟花效果
function createFireworks() {
    const particles = [];
    
    function animate() {
        ctx.fillStyle = 'rgba(0, 0, 0, 0.05)';
        ctx.fillRect(0, 0, canvas.width, canvas.height);
        
        if (Math.random() < 0.15) {
            for(let i=0; i<3; i++) {
                particles.push(new Particle(
                    Math.random() * canvas.width,
                    canvas.height
                ));
            }
        }
        
        particles.forEach((particle, index) => {
            particle.update();
            particle.draw();
            if (particle.y > canvas.height) {
                particles.splice(index, 1);
            }
        });
        
        requestAnimationFrame(animate);
    }
    animate();
}

// 初始化程序
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('start-btn').addEventListener('click', function() {
        document.getElementById('opening').style.display = 'none';
        document.getElementById('question-container').classList.remove('hidden');
        showQuestion();
        initCanvas();
    });
});

window.addEventListener('resize', initCanvas);