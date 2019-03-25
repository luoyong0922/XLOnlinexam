/* 自定义时间格式 */
    Date.prototype.toLocaleString = function() {
            let year = this.getFullYear();
            let month = (this.getMonth()+1 < 10 ? '0'+ (this.getMonth()+1) : this.getMonth()+1 );
            let date = (this.getDate() < 10 ? '0'+ this.getDate() : this.getDate() );
            let hour = this.getHours();
            let minute = (this.getMinutes() < 10 ? '0'+ this.getMinutes() : this.getMinutes() );
            let second = (this.getSeconds() < 10 ? '0'+ this.getSeconds() : this.getSeconds() );
            return year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second;
        };